package org.apache.nifi.provenance;

public enum ProvenanceEventType {

    /**
     * A CREATE event is used when a FlowFile is generated from data that was
     * not received from a remote system or external process
     */
    CREATE,
    /**
     * Indicates a provenance event for receiving data from an external process
     */
    RECEIVE,
    /**
     * Indicates a provenance event for sending data to an external process
     */
    SEND,
    /**
     * Indicates a provenance event for the conclusion of an object's life for
     * some reason other than object expiration
     */
    DROP,
    /**
     * Indicates a provenance event for the conclusion of an object's life due
     * to the fact that the object could not be processed in a timely manner
     */
    EXPIRE,
    /**
     * FORK is used to indicate that one or more FlowFile was derived from a
     * parent FlowFile.
     */
    FORK,
    /**
     * JOIN is used to indicate that a single FlowFile is derived from joining
     * together multiple parent FlowFiles.
     */
    JOIN,
    /**
     * CLONE is used to indicate that a FlowFile is an exact duplicate of its
     * parent FlowFile.
     */
    CLONE,
    /**
     * CONTENT_MODIFIED is used to indicate that a FlowFile's content was
     * modified in some way. When using this Event Type, it is advisable to
     * provide details about how the content is modified.
     */
    CONTENT_MODIFIED,
    /**
     * ATTRIBUTES_MODIFIED is used to indicate that a FlowFile's attributes were
     * modified in some way. This event is not needed when another event is
     * reported at the same time, as the other event will already contain all
     * FlowFile attributes.
     */
    ATTRIBUTES_MODIFIED,
    /**
     * ROUTE is used to show that a FlowFile was routed to a specified
     * {@link nifi.processor.Relationship Relationship} and should provide
     * information about why the FlowFile was routed to this relationship.
     */
    ROUTE,
    /**
     * Indicates a provenance event for adding additional information such as a
     * new linkage to a new URI or UUID
     */
    ADDINFO,
    /**
     * Indicates a provenance event for replaying a FlowFile. The UUID of the
     * event will indicate the UUID of the original FlowFile that is being
     * replayed. The event will contain exactly one Parent UUID that is also the
     * UUID of the FlowFile that is being replayed and exactly one Child UUID
     * that is the UUID of the a newly created FlowFile that will be re-queued
     * for processing.
     */
    REPLAY
}
