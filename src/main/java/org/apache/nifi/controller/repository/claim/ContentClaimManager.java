package org.apache.nifi.controller.repository.claim;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * Responsible for managing all ContentClaims that are used in the application
 */
public interface ContentClaimManager {

    /**
     * Creates a new Content Claim with the given id, container, section, and
     * loss tolerance.
     *
     * @param id
     * @param container
     * @param section
     * @param lossTolerant
     * @return
     */
    ContentClaim newContentClaim(String container, String section, String id, boolean lossTolerant);

    /**
     * Returns the number of FlowFiles that hold a claim to a particular piece
     * of FlowFile content
     *
     * @param claim
     * @return
     */
    int getClaimantCount(ContentClaim claim);

    /**
     * Decreases by 1 the count of how many FlowFiles hold a claim to a
     * particular piece of FlowFile content and returns the new count
     *
     * @param claim
     * @return
     */
    int decrementClaimantCount(ContentClaim claim);

    /**
     * Increases by 1 the count of how many FlowFiles hold a claim to a
     * particular piece of FlowFile content and returns the new count
     *
     * @param claim
     * @return
     */
    int incrementClaimantCount(ContentClaim claim);

    /**
     * Increases by 1 the count of how many FlowFiles hold a claim to a
     * particular piece of FlowFile content and returns the new count.
     *
     * If it is known that the Content Claim whose count is being incremented is
     * a newly created ContentClaim, this method should be called with a value
     * of {@code true} as the second argument, as it may allow the manager to
     * optimize its tasks, knowing that the Content Claim cannot be referenced
     * by any other component
     *
     * @param claim
     * @param newClaim
     * @return
     */
    int incrementClaimantCount(ContentClaim claim, boolean newClaim);

    /**
     * Indicates that the given ContentClaim can now be destroyed by the
     * appropriate Content Repository. This should be done only after it is
     * guaranteed that the FlowFile Repository has been synchronized with its
     * underlying storage component. This way, we avoid the following sequence
     * of events:
     * <ul>
     * <li>FlowFile Repository is updated to indicate that FlowFile F no longer
     * depends on ContentClaim C</li>
     * <li>ContentClaim C is no longer needed and is destroyed</li>
     * <li>The Operating System crashes or there is a power failure</li>
     * <li>Upon restart, the FlowFile Repository was not synchronized with its
     * underlying storage mechanism and as such indicates that FlowFile F needs
     * ContentClaim C.</li>
     * <li>Since ContentClaim C has already been destroyed, it is inaccessible,
     * and FlowFile F's Content is not found, so the FlowFile is removed,
     * resulting in data loss.</li>
     * </ul>
     *
     * <p>
     * Using this method of marking the ContentClaim as destructable only when
     * the FlowFile repository has been synced with the underlying storage
     * mechanism, we can ensure that on restart, we will not point to this
     * unneeded claim. As such, it is now safe to destroy the contents.
     * </p>
     *
     * @param claim
     */
    void markDestructable(ContentClaim claim);

    /**
     * Drains up to {@code maxElements} Content Claims from the internal queue
     * of destructable content claims to the given {@code destination} so that
     * they can be destroyed.
     *
     * @param destination
     * @param maxElements
     */
    void drainDestructableClaims(Collection<ContentClaim> destination, int maxElements);

    /**
     * Drains up to {@code maxElements} Content Claims from the internal queue
     * of destructable content claims to the given {@code destination} so that
     * they can be destroyed. If no ContentClaim is ready to be destroyed at
     * this time, will wait up to the specified amount of time before returning.
     * If, after the specified amount of time, there is still no ContentClaim
     * ready to be destroyed, the method will return without having added
     * anything to the given {@code destination}.
     *
     * @param destination
     * @param maxElements
     * @param timeout
     * @param unit
     */
    void drainDestructableClaims(Collection<ContentClaim> destination, int maxElements, long timeout, TimeUnit unit);

    /**
     * Clears the manager's memory of any and all ContentClaims that it knows
     * about
     */
    void purge();
}
