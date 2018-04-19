package org.apache.nifi.authorization.exception;

/**
 * Represents the case when the user identity already exists.
 */
public class IdentityAlreadyExistsException extends RuntimeException {

    public IdentityAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdentityAlreadyExistsException(String message) {
        super(message);
    }

}
