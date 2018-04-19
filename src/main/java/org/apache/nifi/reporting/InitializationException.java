package org.apache.nifi.reporting;

public class InitializationException extends Exception {

    public InitializationException(String explanation) {
        super(explanation);
    }

    public InitializationException(Throwable cause) {
        super(cause);
    }

    public InitializationException(String explanation, Throwable cause) {
        super(explanation, cause);
    }
}
