package org.apache.nifi.processor.exception;

/**
 * Thrown when a flow file is referenced that is not part of the appropriate
 * session or is not properly accounted for by a transfer or removal within a
 * session. In any event this exception indicates a logic or programming error
 * within the processor interacting with the offending session.
 *
 * @author none
 */
public class FlowFileHandlingException extends ProcessException {

    private static final long serialVersionUID = 1L;

    public FlowFileHandlingException(final String message) {
        super(message);
    }

    public FlowFileHandlingException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
