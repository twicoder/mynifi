package org.apache.nifi.processor.io;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author unattributed
 */
public interface OutputStreamCallback {

    /**
     * Provides a managed output stream for use. The input stream is
     * automatically opened and closed though it is ok to close the stream
     * manually - and quite important if any streams wrapping these streams open
     * resources which should be cleared.
     *
     * @param out
     * @throws IOException
     */
    void process(OutputStream out) throws IOException;

}
