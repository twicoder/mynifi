package org.apache.nifi.authorization.exception;

/**
 * Represents the case when the DN could not be confirmed because it was unable
 * to access the data store.
 */
public class AuthorityAccessException extends RuntimeException {

    public AuthorityAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorityAccessException(String message) {
        super(message);
    }

}