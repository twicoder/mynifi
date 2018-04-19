package org.apache.nifi.processor.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that can be applied to a {@link Processor} in order to associate
 * tags with the processor. These tags do not affect the {@link Processor} in
 * any way but serve as additional documentation and can be used to sort/filter
 * Processors.
 *
 * @author none
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Tags {

    /**
     * @return all tag values associated with the given processor
     */
    public String[] value();
}
