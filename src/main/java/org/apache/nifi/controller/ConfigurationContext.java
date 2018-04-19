package org.apache.nifi.controller;

import java.util.Map;

import org.apache.nifi.components.PropertyDescriptor;
import org.apache.nifi.components.PropertyValue;

/**
 * This context is passed to ControllerServices after the service has been
 * initialized.
 */
public interface ConfigurationContext {

    /**
     * Returns the configured value for the property with the given name
     *
     * @param property
     * @return
     */
    PropertyValue getProperty(PropertyDescriptor property);

    /**
     * Returns an unmodifiable map of all configured properties for this
     * {@link ControllerService}
     *
     * @return
     */
    Map<PropertyDescriptor, String> getProperties();

}
