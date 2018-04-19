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
 * Methods marked with this annotation will be invoked each time the processor
 * is stopped and will be invoked only after the last thread has returned from
 * the <code>onTrigger</code> method.
 * </p>
 *
 * <p>
 * This means that the thread executing in this method will be the only thread
 * executing in any part of the Processor. However, since other threads may
 * later execute other parts of the code, member variables must still be
 * protected appropriately. However, access to multiple variables need not be
 * atomic.
 * </p>
 *
 * <p>
 * To indicate that a method should be called immediately when a processor is no
 * longer scheduled to run, see the {@link OnUnscheduled} annotation.
 * </p>
 *
 * @author none
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface OnStopped {

}
