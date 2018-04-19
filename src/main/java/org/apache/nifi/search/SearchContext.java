package org.apache.nifi.search;

import java.util.Map;
import org.apache.nifi.components.PropertyDescriptor;
import org.apache.nifi.components.PropertyValue;

/**
 *
 */
public interface SearchContext {

    /**
     * Gets the search term.
     *
     * @return
     */
    String getSearchTerm();

    /**
     * Gets the annotation data.
     *
     * @return
     */
    String getAnnotationData();

    /**
     * Returns a PropertyValue that encapsulates the value configured for the
     * given PropertyDescriptor
     *
     * @param property
     * @return
     */
    PropertyValue getProperty(PropertyDescriptor property);

    /**
     * Returns a Map of all configured Properties.
     *
     * @return
     */
    Map<PropertyDescriptor, String> getProperties();
}
