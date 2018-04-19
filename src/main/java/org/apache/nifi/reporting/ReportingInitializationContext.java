package org.apache.nifi.reporting;

import java.util.concurrent.TimeUnit;

import org.apache.nifi.controller.ControllerServiceLookup;
import org.apache.nifi.scheduling.SchedulingStrategy;

/**
 * A ReportingConfiguration provides configuration information to a
 * ReportingTask at the time of initialization
 */
public interface ReportingInitializationContext {

    /**
     * Returns the identifier for this ReportingTask
     *
     * @return
     */
    String getIdentifier();

    /**
     * Returns the configured name for this ReportingTask
     *
     * @return
     */
    String getName();

    /**
     * Returns the amount of time, in the given {@link TimeUnit} that will
     * elapsed between the return of one execution of the
     * {@link ReportingTask}'s
     * {@link ReportingTask#onTrigger(ReportingContext) onTrigger} method and
     * the time at which the method is invoked again. This method will return
     * <code>-1L</code> if the Scheduling Strategy is not set to
     * {@link SchedulingStrategy#TIMER_DRIVEN}
     *
     * @param timeUnit
     * @return
     */
    long getSchedulingPeriod(TimeUnit timeUnit);

    /**
     * Returns the {@link ControllerServiceLookup} which can be used to obtain
     * Controller Services
     *
     * @return
     */
    ControllerServiceLookup getControllerServiceLookup();

    /**
     * Returns a String representation of the scheduling period.
     *
     * @return
     */
    String getSchedulingPeriod();

    /**
     * Returns the {@link SchedulingStrategy} that is used to trigger the task
     * to run
     *
     * @return
     */
    SchedulingStrategy getSchedulingStrategy();
}
