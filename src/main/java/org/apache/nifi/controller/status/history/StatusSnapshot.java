package org.apache.nifi.controller.status.history;

import java.util.Date;
import java.util.Map;

/**
 * A StatusSnapshot represents a Component's status report at some point in time
 */
public interface StatusSnapshot {

    /**
     * Rreturns the point in time for which the status values were obtained
     *
     * @return
     */
    Date getTimestamp();

    /**
     * Returns a Map of MetricDescriptor to value
     *
     * @return
     */
    Map<MetricDescriptor<?>, Long> getStatusMetrics();

    /**
     * Returns a {@link ValueReducer} that is capable of merging multiple
     * StatusSnapshot objects into a single one
     *
     * @return
     */
    ValueReducer<StatusSnapshot, StatusSnapshot> getValueReducer();
}
