package org.apache.nifi.bootstrap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import org.apache.nifi.bootstrap.exception.InvalidCommandException;


public class BootstrapCodec {
    private final RunNiFi runner;
    private final BufferedReader reader;
    private final BufferedWriter writer;

    public BootstrapCodec(final RunNiFi runner, final InputStream in, final OutputStream out) {
        this.runner = runner;
        this.reader = new BufferedReader(new InputStreamReader(in));
        this.writer = new BufferedWriter(new OutputStreamWriter(out));
    }

    public void communicate() throws IOException {
        final String line = reader.readLine();
        final String[] splits = line.split(" ");
        if ( splits.length < 0 ) {
            throw new IOException("Received invalid command from NiFi: " + line);
        }

        final String cmd = splits[0];
        final String[] args;
        if ( splits.length == 1 ) {
            args = new String[0];
        } else {
            args = Arrays.copyOfRange(splits, 1, splits.length);
        }

        try {
            processRequest(cmd, args);
        } catch (final InvalidCommandException ice) {
            throw new IOException("Received invalid command from NiFi: " + line + " : " + ice.getMessage() == null ? "" : "Details: " + ice.toString());
        }
    }

    private void processRequest(final String cmd, final String[] args) throws InvalidCommandException, IOException {
        switch (cmd) {
            case "PORT": {
                if ( args.length != 2 ) {
                    throw new InvalidCommandException();
                }

                final int port;
                try {
                    port = Integer.parseInt( args[0] );
                } catch (final NumberFormatException nfe) {
                    throw new InvalidCommandException("Invalid Port number; should be integer between 1 and 65535");
                }

                if ( port < 1 || port > 65535 ) {
                    throw new InvalidCommandException("Invalid Port number; should be integer between 1 and 65535");
                }

                final String secretKey = args[1];

                runner.setNiFiCommandControlPort(port, secretKey);
                writer.write("OK");
                writer.newLine();
                writer.flush();
            }
            break;
        }
    }
}
