package org.apache.nifi.bootstrap.exception;

public class InvalidCommandException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidCommandException() {
        super();
    }

    public InvalidCommandException(final String message) {
        super(message);
    }

    public InvalidCommandException(final Throwable t) {
        super(t);
    }

    public InvalidCommandException(final String message, final Throwable t) {
        super(message, t);
    }
}
