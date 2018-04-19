package org.apache.nifi.controller.status.history;

public interface ValueMapper<S> {

    Long getValue(S status);

}
