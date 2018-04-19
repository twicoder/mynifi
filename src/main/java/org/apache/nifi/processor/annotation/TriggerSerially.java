package org.apache.nifi.processor.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker annotation a processor implementation can use to indicate that the
 * processor is not thread safe for concurrent execution of its onTrigger()
 * method. By default processors are assumed to be thread safe for concurrent
 * execution.
 *
 * @author none
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TriggerSerially {
}
