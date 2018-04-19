package org.apache.nifi.controller.repository;


import org.apache.nifi.controller.FlowFileQueue;
import org.apache.nifi.controller.repository.claim.ContentClaim;

/**
 * Represents an abstraction of a FlowFile that can be used to track changing
 * state of a FlowFile so that transactionality with repositories can exist
 */
public interface RepositoryRecord {

    /**
     * The FlowFileQueue to which the FlowFile is to be transferred
     *
     * @return
     */
    FlowFileQueue getDestination();

    /**
     * The FlowFileQueue from which the record was pulled
     *
     * @return
     */
    FlowFileQueue getOriginalQueue();

    /**
     * The type of update that this record encapsulates
     *
     * @return
     */
    RepositoryRecordType getType();

    /**
     * The current ContentClaim for the FlowFile
     *
     * @return
     */
    ContentClaim getCurrentClaim();

    /**
     * The original ContentClaim for the FlowFile before any changes were made
     *
     * @return
     */
    ContentClaim getOriginalClaim();

    /**
     * The byte offset into the Content Claim where this FlowFile's content
     * begins
     *
     * @return
     */
    long getCurrentClaimOffset();

    /**
     * The FlowFile being encapsulated by this record
     *
     * @return
     */
    FlowFileRecord getCurrent();

    /**
     * Whether or not the FlowFile's attributes have changed since the FlowFile
     * was pulled from its queue (or created)
     *
     * @return
     */
    boolean isAttributesChanged();

    /**
     * @return <code>true</code> if the FlowFile is not viable and should be
     * aborted (e.g., the content of the FlowFile cannot be found)
     */
    boolean isMarkedForAbort();

    /**
     * If the FlowFile is swapped out of the Java heap space, provides the
     * location of the swap file, or <code>null</code> if the FlowFile is not
     * swapped out
     *
     * @return
     */
    String getSwapLocation();
}
