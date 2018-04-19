package org.apache.nifi.controller;

public interface ControllerServiceInitializationContext {

    /**
     * Returns the identifier associated with the {@link ControllerService} with
     * which this context is associated
     *
     * @return
     */
    String getIdentifier();

    /**
     * Returns the {@link ControllerServiceLookup} which can be used to obtain
     * Controller Services
     *
     * @return
     */
    ControllerServiceLookup getControllerServiceLookup();
}
