package org.apache.nifi.authorization.exception;

/**
 * Represents the exceptional case when an AuthorityProvider fails instantiated.
 *
 * @author unattributed
 */
public class ProviderCreationException extends RuntimeException {

    public ProviderCreationException() {
    }

    public ProviderCreationException(String msg) {
        super(msg);
    }

    public ProviderCreationException(Throwable cause) {
        super(cause);
    }

    public ProviderCreationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
