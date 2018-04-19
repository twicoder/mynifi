package org.apache.nifi.controller.repository;

import org.apache.nifi.controller.repository.claim.ContentClaimManager;
import org.apache.nifi.events.EventReporter;

/**
 * Defines a mechanism by which FlowFiles can be move into external storage or
 * memory so that they can be removed from the Java heap and vice-versa
 */
public interface FlowFileSwapManager {

    /**
     * Starts the Manager's background threads to start swapping FlowFiles in
     * and out of memory
     *
     * @param flowFileRepository the FlowFileRepository that must be notified of
     * any swapping in or out of FlowFiles
     * @param queueProvider the provider of FlowFileQueue's so that FlowFiles
     * can be obtained and restored
     * @param claimManager the ContentClaimManager to use for interacting with
     * Content Claims
     * @param reporter the EventReporter that can be used for notifying users of
     * important events
     */
    void start(FlowFileRepository flowFileRepository, QueueProvider queueProvider, ContentClaimManager claimManager, EventReporter reporter);

    /**
     * Shuts down the manager
     */
    void shutdown();

    /**
     * Removes all Swap information, permanently destroying any FlowFiles that
     * have been swapped out
     */
    void purge();

    /**
     * Notifies FlowFile queues of the number of FlowFiles and content size of
     * all FlowFiles that are currently swapped out
     *
     * @param connectionProvider
     * @param claimManager
     * @return
     */
    long recoverSwappedFlowFiles(QueueProvider connectionProvider, ContentClaimManager claimManager);
}
