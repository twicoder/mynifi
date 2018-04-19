package org.apache.nifi.controller.status.history;

import java.util.List;

public interface ValueReducer<T, R> {

    R reduce(List<T> values);

}
