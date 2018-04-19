package org.apache.nifi.controller.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker annotation a ControllerService implementation can use to indicate a
 * method should be called after all of the properties have been set for the
 * Controller Service. Methods using this annotation must take either 0
 * arguments or a single argument of type
 * {@link nifi.controller.ConfigurationContext ConfigurationContext}.
 *
 * @author none
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface OnConfigured {

}
