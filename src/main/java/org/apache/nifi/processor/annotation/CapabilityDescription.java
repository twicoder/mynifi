package org.apache.nifi.processor.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that may be placed on a processor allowing for a description to be
 * provided. This description can be provided to a user in logs, UI, etc.
 *
 * @author none
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CapabilityDescription {

    String value();
}
