package org.apache.nifi.controller.repository.claim;

/**
 * <p>
 * A ContentClaim is a reference to a given flow file's content. Multiple flow
 * files may reference the same content by both having the same content
 * claim.</p>
 *
 * <p>
 * Must be thread safe</p>
 *
 * @author none
 */
public interface ContentClaim extends Comparable<ContentClaim> {

    /**
     * @return the unique identifier for this claim
     */
    String getId();

    /**
     * @return the container identifier in which this claim is held
     */
    String getContainer();

    /**
     * @return the section within a given container the claim is held
     */
    String getSection();

    /**
     * Specifies whether or not the Claim is loss-tolerant. If so, we will
     * attempt to keep the content but will not sacrifice a great deal of
     * performance to do so.
     *
     * @return
     */
    boolean isLossTolerant();
}
