package org.apache.nifi.controller;

/**
 * Indicates the valid values for the state of a <code>Triggerable</code> entity
 * with respect to scheduling the entity to run.
 */
public enum ScheduledState {

    /**
     * Entity cannot be scheduled to run
     */
    DISABLED,
    /**
     * Entity can be scheduled to run but currently is not
     */
    STOPPED,
    /**
     * Entity is currently scheduled to run
     */
    RUNNING;
}
