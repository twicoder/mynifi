package org.apache.nifi.reporting;

import org.apache.nifi.components.ConfigurableComponent;
import org.apache.nifi.controller.annotation.OnConfigured;

/**
 * Defines a task that is responsible for reporting status information to
 * external destinations. All implementations of this class must have a default
 * constructor.
 *
 * <p>
 * <code>ReportingTask</code>s are discovered using Java's
 * <code>ServiceLoader</code> mechanism. As a result, all implementations must
 * follow these rules:
 *
 * <ul>
 * <li>The implementation must implement this interface.</li>
 * <li>The implementation must have a file named
 * org.apache.nifi.reporting.ReportingTask located within the jar's
 * <code>META-INF/services</code> directory. This file contains a list of
 * fully-qualified class names of all <code>ReportingTask</code>s in the jar,
 * one-per-line.
 * <li>The implementation must support a default constructor.</li>
 * </ul>
 * </p>
 *
 * <p>
 * ReportingTasks are scheduled on a delayed interval with a single thread.
 * Therefore, implementations are not required to be thread-safe.
 * </p>
 *
 * <p>
 * ReportingTasks may choose to annotate a method with the
 * {@link OnConfigured @OnConfigured} annotation. If this is done, that method
 * will be invoked after all properties have been set for the ReportingTask and
 * before it is scheduled to run. If the method throws an Exception, the
 * ReportingTask will be Administratively yielded and will not run for the
 * configured period of time.
 * </p>
 */
public interface ReportingTask extends ConfigurableComponent {

    /**
     * Provides the Reporting Task with access to objects that may be of use
     * throughout the life of the service
     *
     * @param config
     * @throws org.apache.nifi.reporting.InitializationException
     */
    void initialize(ReportingInitializationContext config) throws InitializationException;

    /**
     * This method is called on a scheduled interval to allow the Reporting Task
     * to perform its tasks.
     *
     * @param context
     */
    void onTrigger(ReportingContext context);
}
