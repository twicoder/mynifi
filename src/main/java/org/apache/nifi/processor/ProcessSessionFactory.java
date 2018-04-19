package org.apache.nifi.processor;

/**
 * <p>
 * Factory for creating a {@link ProcessSession}
 * </p>
 *
 * <p>
 * MUST BE THREAD-SAFE</p>
 */
public interface ProcessSessionFactory {

    ProcessSession createSession();
}
