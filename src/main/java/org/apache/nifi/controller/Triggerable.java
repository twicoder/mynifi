package org.apache.nifi.controller;

import java.util.concurrent.TimeUnit;

import org.apache.nifi.processor.ProcessContext;
import org.apache.nifi.processor.ProcessSession;
import org.apache.nifi.processor.ProcessSessionFactory;
import org.apache.nifi.processor.exception.ProcessException;

public interface Triggerable {

    public static final long MINIMUM_SCHEDULING_NANOS = 30000L;

    /**
     * <p>
     * The method called when this processor is triggered to operate by the
     * controller. This method may be called concurrently from different
     * threads. When this method is called depends on how this processor is
     * configured within a controller to be triggered (timing or event
     * based).</p>
     *
     * <p>
     * The processor may commit, roll back, or allow the framework to
     * automatically manage the session. If the sessions are to be managed by
     * the framework (recommended) then what it will do depends on several
     * factors. If the method call returns due to an exception then the session
     * will be rolled back. If the method returns normally then the session will
     * be committed or the framework may use the session again for another
     * processor down stream</p>
     *
     * @param context
     * @param sessionFactory used to generate {@link ProcessSession}s to use
     * for operating on flow files within the repository
     *
     * @throws ProcessException if processing did not complete normally though
     * indicates the problem is an understood potential outcome of processing.
     * The controller/caller will handle these exceptions gracefully such as
     * logging, etc.. If another type of exception is allowed to propagate the
     * controller may no longer trigger this processor to operate as this would
     * indicate a probable coding defect.
     */
    void onTrigger(ProcessContext context, ProcessSessionFactory sessionFactory) throws ProcessException;

    /**
     * Determines the number of concurrent tasks that may be running for this
     * <code>Triggerable</code>.
     *
     * @param taskCount a number of concurrent tasks this processor may have
     * running
     * @throws IllegalArgumentException if the given value is less than 1
     */
    void setMaxConcurrentTasks(int taskCount);

    /**
     * @return the number of tasks that may execute concurrently for this
     * <code>Triggerable</code>.
     */
    int getMaxConcurrentTasks();

    /**
     * Indicates the {@link ScheduledState} of this <code>Triggerable</code>. A
     * value of stopped does NOT indicate that the <code>Triggerable</code> has
     * no active threads, only that it is not currently scheduled to be given
     * any more threads. To determine whether or not the
     * <code>Triggerable</code> has any active threads, see
     * {@link ProcessScheduler#getActiveThreadCount(nifi.connectable.Connectable)}.
     *
     * @return
     */
    ScheduledState getScheduledState();

    /**
     * Indicates whether or not this <code>Triggerable</code> is "running". It
     * is considered "running" if it is scheduled to run OR if it is no longer
     * scheduled to be given threads but the remaining threads from the last
     * invocation of {@link #onTrigger(ProcessContext, ProcessSessionFactory)}
     * have not yet returned
     *
     * @return
     */
    boolean isRunning();

    /**
     * @param timeUnit
     * @return the amount of time between each scheduling period
     */
    long getSchedulingPeriod(TimeUnit timeUnit);

    /**
     * @return a string representation of the time between each scheduling
     * period
     */
    String getSchedulingPeriod();

    /**
     * Updates how often this Triggerable should be triggered to run
     * @param schedulingPeriod
     */
    void setScheduldingPeriod(String schedulingPeriod);
}