package org.apache.nifi.processor.io;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author unattributed
 */
public interface InputStreamCallback {

    /**
     * Provides a managed input stream for use. The input stream is
     * automatically opened and closed though it is ok to close the stream
     * manually.
     *
     * @param in
     * @throws IOException
     */
    void process(InputStream in) throws IOException;

}
