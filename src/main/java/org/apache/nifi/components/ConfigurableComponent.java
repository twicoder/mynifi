package org.apache.nifi.components;

import java.util.Collection;
import java.util.List;

public interface ConfigurableComponent {

    /**
     * Validates a set of properties, returning ValidationResults for any
     * invalid properties. All defined properties will be validated. If they are
     * not included in the in the purposed configuration, the default value will
     * be used.
     *
     * @param context
     * @return Collection of validation result objects for any invalid findings
     * only. If the collection is empty then the component is valid. Guaranteed
     * non-null
     */
    Collection<ValidationResult> validate(ValidationContext context);

    /**
     * Returns the PropertyDescriptor with the given name, if it exists;
     * otherwise, returns <code>null</code>.
     *
     * @param name
     * @return
     */
    PropertyDescriptor getPropertyDescriptor(String name);

    /**
     * Hook method allowing subclasses to eagerly react to a configuration
     * change for the given property descriptor. This method will be invoked
     * regardless of property validity. As an alternative to using this method,
     * a component may simply get the latest value whenever it needs it and if
     * necessary lazily evaluate it. Any throwable that escapes this method will
     * simply be ignored.
     *
     * @param descriptor
     * @param oldValue the value that was previously set, or null if no value
     * was previously set for this property
     * @param newValue the new property value or if null indicates the property
     * was removed
     */
    void onPropertyModified(PropertyDescriptor descriptor, String oldValue, String newValue);

    /**
     * Returns a {@link List} of all {@link PropertyDescriptor}s that this
     * component supports.
     *
     * @return PropertyDescriptor objects this component currently supports
     */
    List<PropertyDescriptor> getPropertyDescriptors();

    /**
     * Returns the unique identifier that the framework assigned to this
     * component
     *
     * @return
     */
    String getIdentifier();
}
