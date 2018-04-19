package org.apache.nifi.controller.repository;

import java.util.Collection;

import org.apache.nifi.controller.FlowFileQueue;

/**
 * Provides a collection of <code>FlowFileQueue</code>s that represents all
 * queues in the current flow
 */
public interface QueueProvider {

    /**
     * Returns all <code>FlowFileQueue</code>s that currently exist in the flow
     *
     * @return
     */
    Collection<FlowFileQueue> getAllQueues();
}
