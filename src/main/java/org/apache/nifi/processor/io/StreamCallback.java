package org.apache.nifi.processor.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author unattributed
 */
public interface StreamCallback {

    /**
     * Provides a managed output stream for use. The input stream is
     * automatically opened and closed though it is ok to close the stream
     * manually - and quite important if any streams wrapping these streams open
     * resources which should be cleared.
     *
     * @param in
     * @param out
     * @throws IOException
     */
    void process(InputStream in, OutputStream out) throws IOException;

}
