package org.apache.nifi.authorization.exception;

/**
 * Represents the case when an identity cannot be confirmed.
 */
public class UnknownIdentityException extends RuntimeException {

    public UnknownIdentityException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownIdentityException(String message) {
        super(message);
    }

}
