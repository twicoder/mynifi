package org.apache.nifi.web;

/**
 * Exception indicating that the desired resource was not found.
 */
@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
