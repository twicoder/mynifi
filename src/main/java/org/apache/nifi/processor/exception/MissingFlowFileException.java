package org.apache.nifi.processor.exception;

/**
 * Thrown to indicate that the content for some FlowFile could not be found.
 * This indicates that the data is gone and likely cannot be restored and any
 * information about the FlowFile should be discarded entirely. This likely
 * indicates some influence from an external process outside the control of the
 * framework and it is not something any processor or the framework can recover
 * so it must discard the object.
 *
 * @author none
 */
public class MissingFlowFileException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MissingFlowFileException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
