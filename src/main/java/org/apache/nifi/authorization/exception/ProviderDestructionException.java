package org.apache.nifi.authorization.exception;

/**
 * Represents the exceptional case when an AuthorityProvider fails destruction.
 *
 * @author unattributed
 */
public class ProviderDestructionException extends RuntimeException {

    public ProviderDestructionException() {
    }

    public ProviderDestructionException(String msg) {
        super(msg);
    }

    public ProviderDestructionException(Throwable cause) {
        super(cause);
    }

    public ProviderDestructionException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
