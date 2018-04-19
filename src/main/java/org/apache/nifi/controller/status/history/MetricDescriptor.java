package org.apache.nifi.controller.status.history;

/**
 * Describes a particular metric that is derived from a Status History
 * @param <T>
 */
public interface MetricDescriptor<T> {

    public enum Formatter {

        COUNT,
        DURATION,
        DATA_SIZE
    };

    /**
     * Specifies how the values should be formatted
     *
     * @return
     */
    Formatter getFormatter();

    /**
     * Returns a human-readable description of the field
     *
     * @return
     */
    String getDescription();

    /**
     * Returns a human-readable label for the field
     *
     * @return
     */
    String getLabel();

    /**
     * Returns the name of a field
     *
     * @return
     */
    String getField();

    /**
     * Returns a {@link ValueMapper} that can be used to extract a value for the
     * status history
     *
     * @return
     */
    ValueMapper<T> getValueFunction();

    /**
     * Returns a {@link ValueReducer} that can reduce multiple StatusSnapshots
     * into a single Long value
     *
     * @return
     */
    ValueReducer<StatusSnapshot, Long> getValueReducer();
}