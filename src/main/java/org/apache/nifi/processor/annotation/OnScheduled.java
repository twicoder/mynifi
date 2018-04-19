package org.apache.nifi.processor.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker annotation a processor implementation can use to indicate a method
 * should be called whenever the processor is scheduled for processing. This
 * will be called before any 'onTrigger' calls and will be called once each time
 * a processor instance is scheduled to run. Methods using this annotation must
 * take either 0 arguments or a single argument of type
 * {@link nifi.processor.SchedulingContext SchedulingContext}.
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
public @interface OnScheduled {
}
