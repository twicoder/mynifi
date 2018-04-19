package org.apache.nifi.provenance.lineage;

import java.util.Date;
import java.util.List;

/**
 *
 */
public interface ComputeLineageResult {

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

    /**
     * Returns the date at which this AsynchronousLineageResult will expire
     *
     * @return
     */
    Date getExpiration();

    /**
     * If an error occurred while computing the lineage, this will return the
     * serialized error; otherwise, returns <code>null</code>.
     *
     * @return
     */
    String getError();

    /**
     * returns an integer between 0 and 100 (inclusive) that indicates what
     * percentage of completion the computation has reached
     *
     * @return
     */
    int getPercentComplete();

    /**
     * Indicates whether or not the lineage has finished running
     *
     * @return
     */
    boolean isFinished();
}
