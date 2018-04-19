package org.apache.nifi.processor.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker annotation a processor implementation can use to indicate that the
 * processor should still be triggered even when it has no data in its work
 * queue. By default, processors which have no non-self incoming edges will be
 * triggered even if there is no work in its queue. However, processors that
 * have non-self incoming edges will only be triggered if they have work in
 * their queue or they present this annotation.
 *
 * @author none
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TriggerWhenEmpty {
}
