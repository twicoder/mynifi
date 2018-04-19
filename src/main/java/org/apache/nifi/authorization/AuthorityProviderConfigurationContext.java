package org.apache.nifi.authorization;

import java.util.Map;

/**
 *
 */
public interface AuthorityProviderConfigurationContext {

    /**
     * The identifier for the authority provider.
     *
     * @return
     */
    String getIdentifier();

    /**
     * Retrieves all properties the component currently understands regardless
     * of whether a value has been set for them or not. If no value is present
     * then its value is null and thus any registered default for the property
     * descriptor applies.
     *
     * @return Map of all properties
     */
    Map<String, String> getProperties();

    /**
     * Retrieves the value the component currently understands for the given
     * PropertyDescriptor. This method does not substitute default
     * PropertyDescriptor values, so the value returned will be null if not set.
     *
     * @param property
     * @return
     */
    String getProperty(String property);
}
