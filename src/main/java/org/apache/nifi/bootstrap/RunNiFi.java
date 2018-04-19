package org.apache.nifi.bootstrap;

import java.io.*;
import java.lang.reflect.Field;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;

public class RunNiFi {

    public static final String DEFAULT_CONFIG_FILE = "./conf/bootstrap.conf";
    public static final String DEFAULT_NIFI_PROPS_FILE = "./conf/nifi.properties";

    public static final String GRACEFUL_SHUTDOWN_PROP = "graceful.shutdown.seconds";
    public static final String DEFAULT_GRACEFUL_SHUTDOWN_VALUE = "20";

    public static final String RUN_AS_PROP = "run.as";

    public static final int MAX_RESTART_ATTEMPTS = 5;
    public static final int STARTUP_WAIT_SECONDS = 60;

    public static final String PING_CMD = "PING";

    private volatile boolean autoRestartNiFi = true;
    private volatile int ccPort = -1;
    private volatile long nifiPid = -1L;
    private volatile String secretKey;
    private volatile ShutdownHook shutdownHook;

    private final Lock lock = new ReentrantLock();
    private final Condition startupCondition = lock.newCondition();


    private final File bootstrapConfigFile;
    private final java.util.logging.Logger logger;

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println();
        System.out.println("java org.apache.nifi.bootstrap.RunNiFi [<-verbose>] <command> [options]");
        System.out.println();
        System.out.println("Valid commands include:");
        System.out.println("");
        System.out.println("Start : Start a new instance of Apache NiFi");
        System.out.println("Stop : Stop a running instance of Apache NiFi");
        System.out.println("Restart : Stop Apache NiFi, if it is running, and then start a new instance");
        System.out.println("Status : Determine if there is a running instance of Apache NiFi");
        System.out.println("Dump : Write a Thread Dump to the file specified by [options], or to the log if no file is given");
        System.out.println("Run : Start a new instance of Apache NiFi and monitor the Process, restarting if the instance dies");
        System.out.println();
    }

    private static String[] shift(final String[] orig) {
        return Arrays.copyOfRange(orig, 1, orig.length);
    }

    public RunNiFi(final File bootstrapConfigFile, final boolean verbose) {
        this.bootstrapConfigFile = bootstrapConfigFile;
        logger = java.util.logging.Logger.getLogger("Bootstrap");
        if ( verbose ) {
            logger.info("Enabling Verbose Output");

            logger.setLevel(Level.FINE);
            final Handler handler = new ConsoleHandler();
            handler.setLevel(Level.FINE);
            logger.addHandler(handler);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        if ( args.length < 1 || args.length > 3 ) {
            printUsage();
            return;
        }

        File dumpFile = null;
        boolean verbose = false;
        if ( args[0].equals("-verbose") ) {
            verbose = true;
            args = shift(args);
        }

        final String cmd = args[0];
        if (cmd.equals("dump") ) {
            if ( args.length > 1 ) {
                dumpFile = new File(args[1]);
            } else {
                dumpFile = null;
            }
        }

        switch (cmd.toLowerCase()) {
            case "start":
            case "run":
            case "stop":
            case "status":
            case "dump":
            case "restart":
                break;
            default:
                printUsage();
                return;
        }

        String configFilename = System.getProperty("org.apache.nifi.bootstrap.config.file");

//        if ( configFilename == null ) {
//            final String nifiHome = System.getenv("NIFI_HOME");
//            if ( nifiHome != null ) {
//                final File nifiHomeFile = new File(nifiHome.trim());
//                final File configFile = new File(nifiHomeFile, DEFAULT_CONFIG_FILE);
//                configFilename = configFile.getAbsolutePath();
//            }
//        }
//
//        if ( configFilename == null ) {
//            configFilename = DEFAULT_CONFIG_FILE;
//        }

        final File configFile = new File(configFilename);

        final RunNiFi runNiFi = new RunNiFi(configFile, verbose);

        switch (cmd.toLowerCase()) {
            case "start":
                runNiFi.start(false);
                break;
            case "run":
                runNiFi.start(true);
                break;
            case "stop":
                runNiFi.stop();
                break;
            case "status":
                runNiFi.status();
                break;
            case "restart":
                runNiFi.stop();
                runNiFi.start(false);
                break;
            case "dump":
                runNiFi.dump(dumpFile);
                break;
        }


        System.out.println("Hello World!");

    }

    public void stop(){}
    public void status(){}
    public void dump(File dumpFile){}


    public File getStatusFile() {
        final File confDir = bootstrapConfigFile.getParentFile();
        File nifiHome = confDir.getParentFile();
//        nifiHome = new File("/Users/renqingwei/code/bigdata/learn/spark/mynifi");
        System.out.println("nifiHome:");
        System.out.println(nifiHome);
        final File bin = new File(nifiHome, "bin");
        System.out.println(bin);
        final File statusFile = new File(bin, "nifi.pid");
        System.out.println(statusFile);
        logger.fine("Status File: " + statusFile);

        return statusFile;
    }

    private Properties loadProperties() throws IOException {
        final Properties props = new Properties();
        final File statusFile = getStatusFile();
        if ( statusFile == null || !statusFile.exists() ) {
            logger.fine("No status file to load properties from");
            return props;
        }

        try (final FileInputStream fis = new FileInputStream(getStatusFile())) {
            props.load(fis);
        }

        logger.fine("Properties: " + props);
        return props;
    }

    private boolean isPingSuccessful(final int port, final String secretKey) {
        logger.fine("Pinging " + port);

        try (final Socket socket = new Socket("localhost", port)) {
            final OutputStream out = socket.getOutputStream();
            out.write((PING_CMD + " " + secretKey + "\n").getBytes(StandardCharsets.UTF_8));
            out.flush();

            logger.fine("Sent PING command");
            socket.setSoTimeout(5000);
            final InputStream in = socket.getInputStream();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            final String response = reader.readLine();
            logger.fine("PING response: " + response);

            return PING_CMD.equals(response);
        } catch (final IOException ioe) {
            return false;
        }
    }

    private boolean isProcessRunning(final String pid) {
        try {
            // We use the "ps" command to check if the process is still running.
            final ProcessBuilder builder = new ProcessBuilder();

            builder.command("ps", "-p", pid);
            final Process proc = builder.start();

            // Look for the pid in the output of the 'ps' command.
            boolean running = false;
            String line;
            try (final InputStream in = proc.getInputStream();
                 final Reader streamReader = new InputStreamReader(in);
                 final BufferedReader reader = new BufferedReader(streamReader)) {

                while ((line = reader.readLine()) != null) {
                    if ( line.trim().startsWith(pid) ) {
                        running = true;
                    }
                }
            }

            // If output of the ps command had our PID, the process is running.
            if ( running ) {
                logger.fine("Process with PID " + pid + " is running");
            } else {
                logger.fine("Process with PID " + pid + " is not running");
            }

            return running;
        } catch (final IOException ioe) {
            System.err.println("Failed to determine if Process " + pid + " is running; assuming that it is not");
            return false;
        }
    }

    private Integer getCurrentPort() throws IOException {
        final Properties props = loadProperties();
        final String portVal = props.getProperty("port");
        if ( portVal == null ) {
            logger.fine("No Port found in status file");
            return null;
        } else {
            logger.fine("Port defined in status file: " + portVal);
        }

        final int port = Integer.parseInt(portVal);
        final boolean success = isPingSuccessful(port, props.getProperty("secret.key"));
        if ( success ) {
            logger.fine("Successful PING on port " + port);
            return port;
        }

        final String pid = props.getProperty("pid");
        logger.fine("PID in status file is " + pid);
        if ( pid != null ) {
            final boolean procRunning = isProcessRunning(pid);
            if ( procRunning ) {
                return port;
            } else {
                return null;
            }
        }

        return null;
    }

    private String replaceNull(final String value, final String replacement) {
        return (value == null) ? replacement : value;
    }

    private File getFile(final String filename, final File workingDir) {
        File file = new File(filename);
        if ( !file.isAbsolute() ) {
            file = new File(workingDir, filename);
        }

        return file;
    }

    void setAutoRestartNiFi(final boolean restart) {
        this.autoRestartNiFi = restart;
    }

    int getNiFiCommandControlPort() {
        return this.ccPort;
    }

    public static boolean isAlive(final Process process) {
        try {
            process.exitValue();
            System.out.println("process.exitValue():" + process.exitValue());

            return false;
        } catch (final IllegalStateException | IllegalThreadStateException itse) {
            return true;
        }
    }

    private synchronized void saveProperties(final Properties nifiProps) throws IOException {
        final File statusFile = getStatusFile();
        if ( statusFile.exists() && !statusFile.delete() ) {
            logger.warning("Failed to delete " + statusFile);
        }

        if ( !statusFile.createNewFile() ) {
            throw new IOException("Failed to create file " + statusFile);
        }

        try {
            final Set<PosixFilePermission> perms = new HashSet<>();
            perms.add(PosixFilePermission.OWNER_READ);
            perms.add(PosixFilePermission.OWNER_WRITE);
            Files.setPosixFilePermissions(statusFile.toPath(), perms);
        } catch (final Exception e) {
            logger.warning("Failed to set permissions so that only the owner can read status file " + statusFile + "; this may allows others to have access to the key needed to communicate with NiFi. Permissions should be changed so that only the owner can read this file");
        }

        try (final FileOutputStream fos = new FileOutputStream(statusFile)) {
            nifiProps.store(fos, null);
            fos.getFD().sync();
        }

        logger.fine("Saved Properties " + nifiProps + " to " + statusFile);
    }

    void setNiFiCommandControlPort(final int port, final String secretKey) {
        this.ccPort = port;
        this.secretKey = secretKey;

        if ( shutdownHook != null ) {
            shutdownHook.setSecretKey(secretKey);
        }

        final File statusFile = getStatusFile();

        final Properties nifiProps = new Properties();
        if ( nifiPid != -1 ) {
            nifiProps.setProperty("pid", String.valueOf(nifiPid));
        }
        nifiProps.setProperty("port", String.valueOf(ccPort));
        nifiProps.setProperty("secret.key", secretKey);

        try {
            saveProperties(nifiProps);
        } catch (final IOException ioe) {
            logger.warning("Apache NiFi has started but failed to persist NiFi Port information to " + statusFile.getAbsolutePath() + " due to " + ioe);
        }

        logger.info("Apache NiFi now running and listening for Bootstrap requests on port " + port);
    }

    private boolean isWindows() {
        final String osName = System.getProperty("os.name");
        return osName != null && osName.toLowerCase().contains("win");
    }

    private Long getPid(final Process process) {
        try {
            final Class<?> procClass = process.getClass();
            final Field pidField = procClass.getDeclaredField("pid");
            pidField.setAccessible(true);
            final Object pidObject = pidField.get(process);

            logger.fine("PID Object = " + pidObject);

            if ( pidObject instanceof Number ) {
                return ((Number) pidObject).longValue();
            }
            return null;
        } catch (final IllegalAccessException | NoSuchFieldException nsfe) {
            logger.fine("Could not find PID for child process due to " + nsfe);
            return null;
        }
    }

    private boolean waitForStart() {
        lock.lock();
        try {
            final long startTime = System.nanoTime();

            while ( ccPort < 1 ) {
                try {
                    startupCondition.await(1, TimeUnit.SECONDS);
                } catch (final InterruptedException ie) {
                    return false;
                }

                final long waitNanos = System.nanoTime() - startTime;
                final long waitSeconds = TimeUnit.NANOSECONDS.toSeconds(waitNanos);
                if (waitSeconds > STARTUP_WAIT_SECONDS) {
                    return false;
                }
            }
        } finally {
            lock.unlock();
        }
        return true;
    }



    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void start(final boolean monitor) throws IOException, InterruptedException {
        final Integer port = getCurrentPort();
        if ( port != null ) {
            System.out.println("Apache NiFi is already running, listening to Bootstrap on port " + port);
            return;
        }


        final ProcessBuilder builder = new ProcessBuilder();


        System.out.println("bootstrapConfigFile:");
        System.out.println(bootstrapConfigFile);

        if ( !bootstrapConfigFile.exists() ) {
            throw new FileNotFoundException(bootstrapConfigFile.getAbsolutePath());
        }

        final Properties properties = new Properties();
        try (final FileInputStream fis = new FileInputStream(bootstrapConfigFile)) {
            properties.load(fis);
        }

        final Map<String, String> props = new HashMap<>();
        props.putAll( (Map) properties );

//        for(String oneKey: props.keySet()){
//            System.out.print(oneKey);
//            System.out.print(":");
//            System.out.println(props.get(oneKey));
//        }

        final String specifiedWorkingDir = props.get("working.dir");
        if ( specifiedWorkingDir != null ) {
            builder.directory(new File(specifiedWorkingDir));
        }

        final File bootstrapConfigAbsoluteFile = bootstrapConfigFile.getAbsoluteFile();
        final File binDir = bootstrapConfigAbsoluteFile.getParentFile();
        System.out.println("binDir:" + binDir);
        final File workingDir = binDir.getParentFile();
        System.out.println("workdingDir:" + workingDir);

        if ( specifiedWorkingDir == null ) {
            builder.directory(workingDir);
        }

        final String libFilename = replaceNull(props.get("lib.dir"), "./lib").trim();
        System.out.println("libFilename:");
        System.out.println(libFilename);
        File libDir = getFile(libFilename, workingDir);

        final String confFilename = replaceNull(props.get("conf.dir"), "./conf").trim();
        File confDir = getFile(confFilename, workingDir);

        String nifiPropsFilename = props.get("props.file");
        if ( nifiPropsFilename == null ) {
            if ( confDir.exists() ) {
                nifiPropsFilename = new File(confDir, "nifi.properties").getAbsolutePath();
            } else {
                nifiPropsFilename = DEFAULT_CONFIG_FILE;
            }
        }

        nifiPropsFilename = nifiPropsFilename.trim();

        final List<String> javaAdditionalArgs = new ArrayList<>();
        for ( final Map.Entry<String, String> entry : props.entrySet() ) {
            final String key = entry.getKey();
            final String value = entry.getValue();

            if ( key.startsWith("java.arg") ) {
                javaAdditionalArgs.add(value);
            }
        }

        final File[] libFiles = libDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(final File dir, final String filename) {
                return filename.toLowerCase().endsWith(".jar");
            }
        });

        if ( libFiles == null || libFiles.length == 0 ) {
            throw new RuntimeException("Could not find lib directory at " + libDir.getAbsolutePath());
        }

        final File[] confFiles = confDir.listFiles();
        if ( confFiles == null || confFiles.length == 0 ) {
            throw new RuntimeException("Could not find conf directory at " + confDir.getAbsolutePath());
        }

        final List<String> cpFiles = new ArrayList<>(confFiles.length + libFiles.length);
        cpFiles.add(confDir.getAbsolutePath());
        for ( final File file : libFiles ) {
            cpFiles.add(file.getAbsolutePath());
        }

        final StringBuilder classPathBuilder = new StringBuilder();
        for (int i=0; i < cpFiles.size(); i++) {
            final String filename = cpFiles.get(i);
            classPathBuilder.append(filename);
            if ( i < cpFiles.size() - 1 ) {
                classPathBuilder.append(File.pathSeparatorChar);
            }
        }

        final String classPath = classPathBuilder.toString();
        String javaCmd = props.get("java");
        if ( javaCmd == null ) {
            javaCmd = "java";
        }

        final NiFiListener listener = new NiFiListener();
        final int listenPort = listener.start(this);

        String runAs = isWindows() ? null : props.get(RUN_AS_PROP);
        if ( runAs != null ) {
            runAs = runAs.trim();
            if ( runAs.isEmpty() ) {
                runAs = null;
            }
        }

        final List<String> cmd = new ArrayList<>();
        if ( runAs != null ) {
            cmd.add("sudo");
            cmd.add("-u");
            cmd.add(runAs);
        }
        cmd.add(javaCmd);
        cmd.add("-classpath");
        cmd.add(classPath);
        cmd.addAll(javaAdditionalArgs);
        cmd.add("-Dnifi.properties.file.path=" + nifiPropsFilename);
        cmd.add("-Dnifi.bootstrap.listen.port=" + listenPort);
        cmd.add("-Dapp=NiFi");
        cmd.add("org.apache.nifi.NiFi");

        builder.command(cmd);

        final StringBuilder cmdBuilder = new StringBuilder();
        for ( final String s : cmd ) {
            cmdBuilder.append(s).append(" ");
        }

        logger.info("Starting Apache NiFi...");
        logger.info("Working Directory: " + workingDir.getAbsolutePath());
        logger.info("Command: " + cmdBuilder.toString());

        if ( monitor ) {
            String gracefulShutdown = props.get(GRACEFUL_SHUTDOWN_PROP);
            if ( gracefulShutdown == null ) {
                gracefulShutdown = DEFAULT_GRACEFUL_SHUTDOWN_VALUE;
            }

            final int gracefulShutdownSeconds;
            try {
                gracefulShutdownSeconds = Integer.parseInt(gracefulShutdown);
            } catch (final NumberFormatException nfe) {
                throw new NumberFormatException("The '" + GRACEFUL_SHUTDOWN_PROP + "' property in Bootstrap Config File " + bootstrapConfigAbsoluteFile.getAbsolutePath() + " has an invalid value. Must be a non-negative integer");
            }

            if ( gracefulShutdownSeconds < 0 ) {
                throw new NumberFormatException("The '" + GRACEFUL_SHUTDOWN_PROP + "' property in Bootstrap Config File " + bootstrapConfigAbsoluteFile.getAbsolutePath() + " has an invalid value. Must be a non-negative integer");
            }

            Process process = builder.start();
            Long pid = getPid(process);
            if ( pid != null ) {
                nifiPid = pid;
                final Properties nifiProps = new Properties();
                nifiProps.setProperty("pid", String.valueOf(nifiPid));
                saveProperties(nifiProps);
            }

            shutdownHook = new ShutdownHook(process, this, secretKey, gracefulShutdownSeconds);
            final Runtime runtime = Runtime.getRuntime();
            runtime.addShutdownHook(shutdownHook);

            while (true) {
                final boolean alive = isAlive(process);

                if ( alive ) {
                    try {
                        Thread.sleep(1000L);
                    } catch (final InterruptedException ie) {
                    }
                } else {
                    try {
                        runtime.removeShutdownHook(shutdownHook);
                    } catch (final IllegalStateException ise) {
                        // happens when already shutting down
                    }

                    if (autoRestartNiFi) {
                        logger.warning("Apache NiFi appears to have died. Restarting...");
                        process = builder.start();

                        pid = getPid(process);
                        if ( pid != null ) {
                            nifiPid = pid;
                            final Properties nifiProps = new Properties();
                            nifiProps.setProperty("pid", String.valueOf(nifiPid));
                            saveProperties(nifiProps);
                        }

                        shutdownHook = new ShutdownHook(process, this, secretKey, gracefulShutdownSeconds);
                        runtime.addShutdownHook(shutdownHook);

                        final boolean started = waitForStart();

                        if ( started ) {
                            logger.info("Successfully started Apache NiFi" + (pid == null ? "" : " with PID " + pid));
                        } else {
                            logger.severe("Apache NiFi does not appear to have started");
                        }
                    } else {
                        return;
                    }
                }
            }
        } else {
            final Process process = builder.start();
            final Long pid = getPid(process);

            if ( pid != null ) {
                nifiPid = pid;
                final Properties nifiProps = new Properties();
                nifiProps.setProperty("pid", String.valueOf(nifiPid));
                saveProperties(nifiProps);
            }

            boolean started = waitForStart();

            if ( started ) {
                logger.info("Successfully started Apache NiFi" + (pid == null ? "" : " with PID " + pid));
            } else {
                logger.severe("Apache NiFi does not appear to have started");
            }

            listener.stop();
        }
    }
}
