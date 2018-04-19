package org.apache.nifi.processor.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Marker annotation a processor implementation can use to indicate a method
 * should be called whenever the processor is no longer scheduled to run.
 * Methods marked with this annotation will be invoked each time the framework
 * is notified to stop scheduling the processor. This method is invoked as other
 * threads are potentially running. To invoke a method after all threads have
 * finished processing, see the {@link OnStopped} annotation.
 * </p>
 *
 * If any method annotated with this annotation throws, the processor will not
 * be scheduled to run.
 *
 * @author none
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface OnUnscheduled {
}
