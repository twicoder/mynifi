package org.apache.nifi.provenance.search;

import java.util.Date;
import java.util.List;

import org.apache.nifi.provenance.ProvenanceEventRecord;

public interface QueryResult {

    /**
     * Returns the Provenance events that match the query (up to the limit
     * specified in the query)
     *
     * @return
     */
    List<ProvenanceEventRecord> getMatchingEvents();

    /**
     * Returns the total number of Provenance Events that hit
     *
     * @return
     */
    long getTotalHitCount();

    /**
     * Returns the number of milliseconds the query took to run
     *
     * @return
     */
    long getQueryTime();

    /**
     * Returns the date at which this QueryResult will expire
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
     * percentage of completion the query has reached
     *
     * @return
     */
    int getPercentComplete();

    /**
     * Indicates whether or not the query has finished running
     *
     * @return
     */
    boolean isFinished();
}
