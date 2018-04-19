package org.apache.nifi.processor.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker annotation a processor implementation can use to indicate a method
 * should be called whenever the processor is removed from the graph. This
 * method will be called once for the entire life of a processor instance unless
 * an invocation of this method throws any Throwable.
 *
 * If any method annotated with this annotation throws, the processor will not
 * be removed from the graph.
 *
 * @author none
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface OnRemoved {
}
