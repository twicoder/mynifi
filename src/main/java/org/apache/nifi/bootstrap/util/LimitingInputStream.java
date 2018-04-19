package org.apache.nifi.bootstrap.util;

import java.io.IOException;
import java.io.InputStream;

public class LimitingInputStream extends InputStream {

    private final InputStream in;
    private final long limit;
    private long bytesRead = 0;

    public LimitingInputStream(final InputStream in, final long limit) {
        this.in = in;
        this.limit = limit;
    }

    @Override
    public int read() throws IOException {
        if (bytesRead >= limit) {
            return -1;
        }

        final int val = in.read();
        if (val > -1) {
            bytesRead++;
        }
        return val;
    }

    @Override
    public int read(final byte[] b) throws IOException {
        if (bytesRead >= limit) {
            return -1;
        }

        final int maxToRead = (int) Math.min(b.length, limit - bytesRead);

        final int val = in.read(b, 0, maxToRead);
        if (val > 0) {
            bytesRead += val;
        }
        return val;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (bytesRead >= limit) {
            return -1;
        }

        final int maxToRead = (int) Math.min(len, limit - bytesRead);

        final int val = in.read(b, off, maxToRead);
        if (val > 0) {
            bytesRead += val;
        }
        return val;
    }

    @Override
    public long skip(final long n) throws IOException {
        final long skipped = in.skip(Math.min(n, limit - bytesRead));
        bytesRead += skipped;
        return skipped;
    }

    @Override
    public int available() throws IOException {
        return in.available();
    }

    @Override
    public void close() throws IOException {
        in.close();
    }

    @Override
    public void mark(int readlimit) {
        in.mark(readlimit);
    }

    @Override
    public boolean markSupported() {
        return in.markSupported();
    }

    @Override
    public void reset() throws IOException {
        in.reset();
    }
}
