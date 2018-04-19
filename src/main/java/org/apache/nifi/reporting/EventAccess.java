package org.apache.nifi.reporting;

import org.apache.nifi.controller.status.ProcessGroupStatus;
import org.apache.nifi.provenance.ProvenanceEventRecord;
import org.apache.nifi.provenance.ProvenanceEventRepository;

import java.io.IOException;
import java.util.List;

public interface EventAccess {

    /**
     * Gets the status for all components in this Controller.
     *
     * @return
     */
    ProcessGroupStatus getControllerStatus();

    /**
     * Convenience method to obtain Provenance Events starting with (and
     * including) the given ID. If no event exists with that ID, the first event
     * to be returned will be have an ID greater than <code>firstEventId</code>.
     *
     * @param firstEventId the ID of the first event to obtain
     * @param maxRecords the maximum number of records to obtain
     * @return
     * @throws java.io.IOException
     */
    List<ProvenanceEventRecord> getProvenanceEvents(long firstEventId, final int maxRecords) throws IOException;

    /**
     * Returns the Provenance Event Repository
     *
     * @return
     */
    ProvenanceEventRepository getProvenanceRepository();
}
