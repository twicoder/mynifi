package org.apache.nifi.bootstrap;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class ShutdownHook extends Thread {
    private final Process nifiProcess;
    private final RunNiFi runner;
    private final int gracefulShutdownSeconds;

    private volatile String secretKey;

    public ShutdownHook(final Process nifiProcess, final RunNiFi runner, final String secretKey, final int gracefulShutdownSeconds) {
        this.nifiProcess = nifiProcess;
        this.runner = runner;
        this.secretKey = secretKey;
        this.gracefulShutdownSeconds = gracefulShutdownSeconds;
    }

    void setSecretKey(final String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public void run() {
        runner.setAutoRestartNiFi(false);
        final int ccPort = runner.getNiFiCommandControlPort();
        if ( ccPort > 0 ) {
            System.out.println("Initiating Shutdown of NiFi...");

            try {
                final Socket socket = new Socket("localhost", ccPort);
                final OutputStream out = socket.getOutputStream();
                out.write(("SHUTDOWN " + secretKey + "\n").getBytes(StandardCharsets.UTF_8));
                out.flush();

                socket.close();
            } catch (final IOException ioe) {
                System.out.println("Failed to Shutdown NiFi due to " + ioe);
            }
        }

        System.out.println("Waiting for Apache NiFi to finish shutting down...");
        final long startWait = System.nanoTime();
        while ( RunNiFi.isAlive(nifiProcess) ) {
            final long waitNanos = System.nanoTime() - startWait;
            final long waitSeconds = TimeUnit.NANOSECONDS.toSeconds(waitNanos);
            if ( waitSeconds >= gracefulShutdownSeconds && gracefulShutdownSeconds > 0 ) {
                if ( RunNiFi.isAlive(nifiProcess) ) {
                    System.out.println("NiFi has not finished shutting down after " + gracefulShutdownSeconds + " seconds. Killing process.");
                    nifiProcess.destroy();
                }
                break;
            } else {
                try {
                    Thread.sleep(1000L);
                } catch (final InterruptedException ie) {}
            }
        }

        final File statusFile = runner.getStatusFile();
        if ( !statusFile.delete() ) {
            System.err.println("Failed to delete status file " + statusFile.getAbsolutePath() + "; this file should be cleaned up manually");
        }
    }
}