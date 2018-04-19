package org.apache.nifi.provenance.lineage;

import java.util.List;

/**
 * A Data Structure for representing a Directed Graph that depicts the lineage
 * of a FlowFile and all events that occurred for the FlowFile
 */
public interface Lineage {

    /**
     * Returns all nodes for the graph
     *
     * @return
     */
    public List<LineageNode> getNodes();

    /**
     * Returns all links for the graph
     *
     * @return
     */
    public List<LineageEdge> getEdges();

}