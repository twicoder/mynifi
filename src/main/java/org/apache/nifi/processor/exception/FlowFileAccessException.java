package org.apache.nifi.processor.exception;

/**
 * Indicates an issue occurred while accessing the content of a FlowFile, such
 * as an IOException.
 *
 * @author none
 */
public class FlowFileAccessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FlowFileAccessException(final String message) {
        super(message);
    }

    public FlowFileAccessException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
