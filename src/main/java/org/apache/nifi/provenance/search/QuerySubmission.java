package org.apache.nifi.provenance.search;

import java.util.Date;

public interface QuerySubmission {

    /**
     * Returns the query that this submission pertains to
     *
     * @return
     */
    Query getQuery();

    /**
     * Returns the {@link QueryResult} for this query. Note that the result is
     * only a partial result if the result of calling
     * {@link QueryResult#isFinished()} is <code>false</code>.
     *
     * @return
     */
    QueryResult getResult();

    /**
     * Returns the date at which this query was submitted
     *
     * @return
     */
    Date getSubmissionTime();

    /**
     * Returns the generated identifier for this query result
     *
     * @return
     */
    String getQueryIdentifier();

    /**
     * Cancels the query
     */
    void cancel();

    /**
     * @return <code>true</code> if {@link #cancel()} has been called,
     * <code>false</code> otherwise
     */
    boolean isCanceled();
}
