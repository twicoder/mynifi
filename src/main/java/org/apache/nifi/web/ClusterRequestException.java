package org.apache.nifi.web;

/**
 */
public class ClusterRequestException extends RuntimeException {

    public ClusterRequestException(Throwable cause) {
        super(cause);
    }

    public ClusterRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClusterRequestException(String message) {
        super(message);
    }

    public ClusterRequestException() {
    }

}
