package org.apache.nifi.web;

/**
 * Exception indicating that the client has included an old revision in their
 * request.
 */
@SuppressWarnings("serial")
public class InvalidRevisionException extends RuntimeException {

    public InvalidRevisionException(String message) {
        super(message);
    }

    public InvalidRevisionException(String message, Throwable cause) {
        super(message, cause);
    }
}