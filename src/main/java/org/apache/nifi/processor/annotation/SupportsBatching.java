package org.apache.nifi.processor.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker annotation a Processor implementation can use to indicate that users
 * should be able to supply a Batch Duration for the Processor. If a Processor
 * uses this annotation, it is allowing the Framework to batch
 * {@link nifi.processor.ProcessSession ProcessSession}s' commits, as well as
 * allowing the Framework to return the same ProcessSession multiple times from
 * subsequent calls to
 * {@link nifi.processor.ProcessSessionFactory ProcessSessionFactory}.{@link nifi.processor.ProcessSessionFactory#createSession() createSession()}.
 *
 * When this Annotation is used, it is important to note that calls to
 * {@link nifi.processor.ProcessSession#commit() ProcessSession.commit()} may
 * not provide a guarantee that the data has been safely stored in NiFi's
 * Content Repository or FlowFile Repository. Therefore, it is not appropriate,
 * for instance, to use this annotation if the Processor will call
 * ProcessSession.commit() to ensure data is persisted before deleting the data
 * from a remote source.
 *
 * @author none
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SupportsBatching {

}
