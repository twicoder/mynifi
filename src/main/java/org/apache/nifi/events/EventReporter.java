package org.apache.nifi.events;

import org.apache.nifi.reporting.Severity;

/**
 * Implementations MUST be thread-safe
 */
public interface EventReporter {

    void reportEvent(Severity severity, String category, String message);
}
