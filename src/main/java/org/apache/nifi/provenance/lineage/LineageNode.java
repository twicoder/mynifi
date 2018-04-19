package org.apache.nifi.provenance.lineage;

public interface LineageNode {

    /**
     * Returns the identifier of the Clustered NiFi Node that generated the
     * event
     *
     * @return
     */
    String getClusterNodeIdentifier();

    /**
     * Returns the type of the LineageNode
     *
     * @return
     */
    LineageNodeType getNodeType();

    /**
     * Returns the UUID of the FlowFile for which this Node was created
     *
     * @return
     */
    String getFlowFileUuid();

    /**
     * Returns the UUID for this LineageNode.
     *
     * @return
     */
    String getIdentifier();

    /**
     * Returns the timestamp that corresponds to this Node. The meaning of the
     * timestamp may differ between implementations. For example, a
     * {@link ProvenanceEventLineageNode}'s timestamp indicates the time at
     * which the event occurred. However, for a Node that reperesents a
     * FlowFile, for example, the timestamp may represent the time at which the
     * FlowFile was created.
     *
     * @return
     */
    long getTimestamp();
}
