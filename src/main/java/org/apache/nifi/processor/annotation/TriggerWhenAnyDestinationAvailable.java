package org.apache.nifi.processor.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker annotation a processor implementation can use to indicate that the
 * processor is to be triggered if any of its destinations has available space
 * for incoming FlowFiles. By default processors are triggered only when all
 * destinations report that they have available space.
 *
 * @author none
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TriggerWhenAnyDestinationAvailable {

}
