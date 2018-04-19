package org.apache.nifi.components;

import java.util.Map;

import org.apache.nifi.controller.ControllerService;
import org.apache.nifi.controller.ControllerServiceLookup;
import org.apache.nifi.expression.ExpressionLanguageCompiler;

public interface ValidationContext {

    /**
     * Returns the {@link ControllerServiceLookup} which can be used to obtain
     * Controller Services
     *
     * @return
     */
    ControllerServiceLookup getControllerServiceLookup();

    /**
     * Returns a ValidationContext that is appropriate for validating the given
     * {@link ControllerService}
     *
     * @param controllerService
     * @return
     */
    ValidationContext getControllerServiceValidationContext(ControllerService controllerService);

    /**
     * Creates and returns a new {@link ExpressionLanguageCompiler} that can be
     * used to compile & evaluate Attribute Expressions
     *
     * @return
     */
    ExpressionLanguageCompiler newExpressionLanguageCompiler();

    /**
     * Returns a PropertyValue that encapsulates the value configured for the
     * given PropertyDescriptor
     *
     * @param property
     * @return
     */
    PropertyValue getProperty(PropertyDescriptor property);

    /**
     * Returns a PropertyValue that represents the given value
     *
     * @param value
     * @return
     */
    PropertyValue newPropertyValue(String value);

    /**
     * Returns a Map of all configured Properties.
     *
     * @return
     */
    Map<PropertyDescriptor, String> getProperties();

    /**
     * Returns the currently configured Annotation Data
     *
     * @return
     */
    String getAnnotationData();
}
