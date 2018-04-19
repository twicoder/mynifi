package org.apache.nifi.provenance.lineage;

import java.util.Collection;
import java.util.Date;

public interface ComputeLineageSubmission {

    /**
     * Returns the {@link ComputeLineageResult} that contains the results. The
     * results may be partial if a call to
     * {@link ComputeLineageResult#isFinished()} returns <code>false</code>.
     *
     * @return
     */
    ComputeLineageResult getResult();

    /**
     * Returns the date at which this lineage was submitted
     *
     * @return
     */
    Date getSubmissionTime();

    /**
     * Returns the generated identifier for this lineage result
     *
     * @return
     */
    String getLineageIdentifier();

    /**
     * Cancels the lineage computation
     */
    void cancel();

    /**
     * @return <code>true</code> if {@link #cancel()} has been called,
     * <code>false</code> otherwise
     */
    boolean isCanceled();

    /**
     * Returns the type of Lineage Computation that was submitted
     *
     * @return
     */
    LineageComputationType getLineageComputationType();

    /**
     * If the Lineage Computation Type of this submission is
     * {@link LineageComputationType.EXPAND_CHILDREN} or
     * {@link LineageComputationType.EXPAND_PARENTS}, indicates the ID event
     * that is to be expanded; otherwise, returns <code>null</code>.
     *
     * @return
     */
    Long getExpandedEventId();

    /**
     * Returns all FlowFile UUID's that are encapsulated in this lineage
     * computation submission
     *
     * @return
     */
    Collection<String> getLineageFlowFileUuids();
}
