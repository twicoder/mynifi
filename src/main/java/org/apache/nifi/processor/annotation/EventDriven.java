package org.apache.nifi.processor.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Annotation that may be placed on a Processor that indicates to the framework
 * that the Processor is eligible to be scheduled to run based on the occurrence
 * of an "Event" (e.g., when a FlowFile is enqueued in an incoming Connection),
 * rather than being triggered periodically.
 * </p>
 *
 * <p>
 * This Annotation should not be used in conjunction with
 * {@link TriggerSerially} or {@link TriggerWhenEmpty}. If this Annotation is
 * used with either of these other Annotations, the Processor will not be
 * eligible to be scheduled in Event-Driven mode.
 * </p>
 *
 * @author none
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface EventDriven {

}
