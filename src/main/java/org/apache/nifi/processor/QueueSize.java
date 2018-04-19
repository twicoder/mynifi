package org.apache.nifi.processor;

/**
 *
 * @author
 */
public class QueueSize {

    private final int objectCount;
    private final long totalSizeBytes;

    public QueueSize(final int numberObjects, final long totalSizeBytes) {
        if (numberObjects < 0 || totalSizeBytes < 0) {
            throw new IllegalArgumentException();
        }
        objectCount = numberObjects;
        this.totalSizeBytes = totalSizeBytes;
    }

    /**
     * @return number of objects present on the queue
     */
    public int getObjectCount() {
        return objectCount;
    }

    /**
     * @return total size in bytes of the content for the data on the queue
     */
    public long getByteCount() {
        return totalSizeBytes;
    }
}
