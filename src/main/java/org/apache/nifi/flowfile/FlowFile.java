package org.apache.nifi.flowfile;

import java.util.Map;
import java.util.Set;

/**
 * <p>
 * A flow file is a logical notion of an item in a flow with its associated
 * attributes and identity which can be used as a reference for its actual
 * content.</p>
 *
 * <b>All FlowFile implementations must be Immutable - Thread safe.</b>
 */
public interface FlowFile extends Comparable<FlowFile> {

    /**
     * @return the unique identifier for this flow file
     */
    long getId();

    /**
     * @return the date at which the flow file entered the flow
     */
    long getEntryDate();

    /**
     * @return the date at which the origin of this FlowFile entered the flow.
     * For example, if FlowFile Z were derived from FlowFile Y and FlowFile Y
     * was derived from FlowFile X, this date would be the entryDate (see
     * {@link #getEntryDate()} of FlowFile X.
     */
    long getLineageStartDate();

    /**
     * Returns the time at which the FlowFile was most recently added to a
     * FlowFile queue, or {@code null} if the FlowFile has never been enqueued.
     * This value will always be populated before it is passed to a
     * {@link FlowFilePrioritizer}.
     *
     * @return
     */
    Long getLastQueueDate();

    /**
     * @return a set of identifiers that are unique to this FlowFile's lineage.
     * If FlowFile X is derived from FlowFile Y, both FlowFiles will have the
     * same value for the Lineage Claim ID.
     *
     * <p>
     * If a FlowFile is derived from multiple "parent" FlowFiles, all of the
     * parents' Lineage Identifiers will be in the set.
     * </p>
     */
    Set<String> getLineageIdentifiers();

    /**
     * @return true if flow file is currently penalized; false otherwise;
     */
    boolean isPenalized();

    /**
     * Obtains the attribute value for the given key
     *
     * @param key
     * @return value if found; null otherwise
     */
    String getAttribute(String key);

    /**
     * @return size of flow file contents in bytes
     */
    long getSize();

    /**
     * @return an unmodifiable map of the flow file attributes
     */
    Map<String, String> getAttributes();

    public static class KeyValidator {

        public static String validateKey(final String key) {
            // We used to validate the key by disallowing a handful of keywords, but this requirement no longer exists.
            // Therefore this method simply verifies that the key is not empty.
            if (key == null) {
                throw new IllegalArgumentException("Invalid attribute key: null");
            }
            if (key.trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid attribute key: <Empty String>");
            }
            return key;
        }
    }
}
