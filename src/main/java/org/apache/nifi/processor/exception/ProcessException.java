package org.apache.nifi.processor.exception;

/**
 * Indicates an issue occurred in user code while processing a FlowFile.
 *
 * @author none
 */
public class ProcessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProcessException() {
        super();
    }

    public ProcessException(final String message) {
        super(message);
    }

    public ProcessException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ProcessException(final Throwable cause) {
        super(cause);
    }

}
