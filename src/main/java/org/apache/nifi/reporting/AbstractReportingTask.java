package org.apache.nifi.reporting;

import java.util.concurrent.TimeUnit;

import org.apache.nifi.components.AbstractConfigurableComponent;
import org.apache.nifi.controller.ControllerServiceLookup;
import org.apache.nifi.processor.ProcessorInitializationContext;

public abstract class AbstractReportingTask extends AbstractConfigurableComponent implements ReportingTask {

    private String identifier;
    private String name;
    private long schedulingNanos;
    private ControllerServiceLookup serviceLookup;

    @Override
    public final void initialize(final ReportingInitializationContext config) throws InitializationException {
        identifier = config.getIdentifier();
        name = config.getName();
        schedulingNanos = config.getSchedulingPeriod(TimeUnit.NANOSECONDS);
        serviceLookup = config.getControllerServiceLookup();

        init(config);
    }

    /**
     * Returns the {@link ControllerServiceLookup} that was passed to the
     * {@link #init(ProcessorInitializationContext)} method
     *
     * @return
     */
    protected final ControllerServiceLookup getControllerServiceLookup() {
        return serviceLookup;
    }

    /**
     * Returns the identifier of this Reporting Task
     *
     * @return
     */
    @Override
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Returns the name of this Reporting Task
     *
     * @return
     */
    protected String getName() {
        return name;
    }

    /**
     * Returns the amount of times that elapses between the moment that this
     * ReportingTask finishes its invocation of
     * {@link #onTrigger(ReportingContext)} and the next time that
     * {@link #onTrigger(ReportingContext)} is called.
     *
     * @param timeUnit
     * @return
     */
    protected long getSchedulingPeriod(final TimeUnit timeUnit) {
        return timeUnit.convert(schedulingNanos, TimeUnit.NANOSECONDS);
    }

    /**
     * Provides a mechanism by which subclasses can perform initialization of
     * the Reporting Task before it is scheduled to be run
     *
     * @param config
     * @throws InitializationException
     */
    protected void init(final ReportingInitializationContext config) throws InitializationException {
    }

}
