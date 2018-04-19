package org.apache.nifi.controller.repository;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.nifi.controller.FlowFileQueue;
import org.apache.nifi.controller.repository.claim.ContentClaimManager;

/**
 * Implementations must be thread safe
 *
 * @author none
 */
public interface FlowFileRepository extends Closeable {

    /**
     * Initializes the Content Repository, providing to it the
     * ContentClaimManager that is to be used for interacting with Content
     * Claims
     *
     * @param claimManager
     * @throws java.io.IOException
     */
    void initialize(ContentClaimManager claimManager) throws IOException;

    /**
     * Returns the maximum number of bytes that can be stored in the underlying
     * storage mechanism
     *
     * @return
     *
     * @throws IOException
     */
    long getStorageCapacity() throws IOException;

    /**
     * Returns the number of bytes currently available for use by the underlying
     * storage mechanism
     *
     * @return
     *
     * @throws IOException
     */
    long getUsableStorageSpace() throws IOException;

    /**
     * Updates the repository with the given RepositoryRecords.
     *
     * @param records the records to update the repository with
     * @throws java.io.IOException
     */
    void updateRepository(Collection<RepositoryRecord> records) throws IOException;

    /**
     * Loads all flow files found within the repository, establishes the content
     * claims and their reference count
     *
     * @param queueProvider the provider of FlowFile Queues into which the
     * FlowFiles should be enqueued
     * @param minimumSequenceNumber specifies the minimum value that should be
     * returned by a call to {@link #getNextFlowFileSequence()}
     *
     * @return index of highest flow file identifier
     * @throws IOException
     */
    long loadFlowFiles(QueueProvider queueProvider, long minimumSequenceNumber) throws IOException;

    /**
     * @return <code>true</code> if the Repository is volatile (i.e., its data
     * is lost upon application restart), <code>false</code> otherwise
     */
    boolean isVolatile();

    /**
     * @return the next ID in sequence for creating <code>FlowFile</code>s.
     */
    long getNextFlowFileSequence();

    /**
     * @return the max ID of all <code>FlowFile</code>s that currently exist in
     * the repository.
     * @throws IOException
     */
    long getMaxFlowFileIdentifier() throws IOException;

    /**
     * Updates the Repository to indicate that the given FlowFileRecords were
     * Swapped Out of memory
     *
     * @param swappedOut the FlowFiles that were swapped out of memory
     * @param flowFileQueue the queue that the FlowFiles belong to
     * @param swapLocation the location to which the FlowFiles were swapped
     *
     * @throws IOException
     */
    void swapFlowFilesOut(List<FlowFileRecord> swappedOut, FlowFileQueue flowFileQueue, String swapLocation) throws IOException;

    /**
     * Updates the Repository to indicate that the given FlowFileRecpords were
     * Swapped In to memory
     *
     * @param swapLocation the location (e.g., a filename) from which FlowFiles
     * were recovered
     * @param flowFileRecords the records that were swapped in
     * @param flowFileQueue the queue that the FlowFiles belong to
     *
     * @throws IOException
     */
    void swapFlowFilesIn(String swapLocation, List<FlowFileRecord> flowFileRecords, FlowFileQueue flowFileQueue) throws IOException;
}
