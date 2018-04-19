package org.apache.nifi.processor;

import org.apache.nifi.controller.ControllerServiceLookup;
import org.apache.nifi.logging.ProcessorLog;

/**
 * <p>
 * The <code>ProcessorInitializationContext</code> provides
 * {@link nifi.processor.Processor Processor}s access to objects that may be of
 * use throughout the life of the Processor.
 * </p>
 */
public interface ProcessorInitializationContext {

    /**
     * Returns the unique identifier for this processor
     *
     * @return
     */
    String getIdentifier();

    /**
     * Returns a {@link ProcessorLog} that is tied to this processor that can be
     * used to log events
     *
     * @return
     */
    ProcessorLog getLogger();

    /**
     * Returns the {@link ControllerServiceLookup} which can be used to obtain
     * Controller Services
     *
     * @return
     */
    ControllerServiceLookup getControllerServiceLookup();
}
