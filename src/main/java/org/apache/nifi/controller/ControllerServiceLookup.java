package org.apache.nifi.controller;

import java.util.Set;


public interface ControllerServiceLookup {

    /**
     * Returns the ControllerService that is registered with the given
     * identifier
     *
     * @param serviceIdentifier
     * @return
     */
    ControllerService getControllerService(String serviceIdentifier);

    /**
     * Returns <code>true</code> if the Controller Service with the given
     * identifier is enabled, <code>false</code> otherwise. If the given
     * identifier is not known by this ControllerServiceLookup, returns
     * <code>false</code>
     *
     * @param serviceIdentifier
     * @return
     */
    boolean isControllerServiceEnabled(String serviceIdentifier);

    /**
     * Returns <code>true</code> if the given Controller Service is enabled,
     * <code>false</code> otherwise. If the given Controller Service is not
     * known by this ControllerServiceLookup, returns <code>false</code>
     *
     * @param service
     * @return
     */
    boolean isControllerServiceEnabled(ControllerService service);

    /**
     * Returns the set of all Controller Service Identifiers whose Controller
     * Service is of the given type. The class specified MUST be an interface,
     * or an IllegalArgumentExcption will be thrown
     *
     * @param serviceType
     * @return
     *
     * @throws IllegalArgumentException if the given class is not an interface
     */
    Set<String> getControllerServiceIdentifiers(Class<? extends ControllerService> serviceType) throws IllegalArgumentException;

}
