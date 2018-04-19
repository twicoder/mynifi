package org.apache.nifi.controller.status.history;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Represents a collection of historical status values for a component
 */
public interface StatusHistory {

    /**
     * Returns a Date indicating when this report was generated
     *
     * @return
     */
    Date getDateGenerated();

    /**
     * Returns a Map of component field names and their values. The order in
     * which these values are displayed is dependent on the natural ordering of
     * the Map returned.
     *
     * @return
     */
    Map<String, String> getComponentDetails();

    /**
     * A List of snapshots for a given component
     *
     * @return
     */
    List<StatusSnapshot> getStatusSnapshots();
}
