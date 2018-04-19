package org.apache.nifi.processor.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker annotation a processor implementation can use to indicate that its
 * operations on flow files can be safely repeated across process sessions. If a
 * processor has this annotation and it allows the framework to manage session
 * commit and rollback then the framework may elect to cascade a
 * <code>ProcessSession</code> given to this processor's onTrigger method to the
 * onTrigger method of another processor. It can do this knowing that if
 * something fails along a series of processors using this same session that it
 * can all be safely rolled back without any ill effects on external services
 * which could not be rolled back and thus all the processes could be safely
 * repeated (implied idempotent behavior).
 *
 * @author none
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SideEffectFree {
}