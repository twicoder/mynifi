package org.apache.nifi.controller.repository;

import org.apache.nifi.controller.repository.claim.ContentClaim;
import org.apache.nifi.flowfile.FlowFile;

/**
 * <code>FlowFileRecord</code> is a sub-interface of <code>FlowFile</code> and
 * is used to provide additional information about FlowFiles that provide
 * valuable information to the framework but should be hidden from components
 */
public interface FlowFileRecord extends FlowFile {

    /**
     * Returns the time (in millis since epoch) at which this FlowFile should no
     * longer be penalized.
     *
     * @return
     */
    long getPenaltyExpirationMillis();

    /**
     * Returns the {@link ContentClaim} that holds the FlowFile's content
     *
     * @return
     */
    ContentClaim getContentClaim();

    /**
     * Returns the byte offset into the {@link ContentClaim} at which the
     * FlowFile's content occurs. This mechanism allows multiple FlowFiles to
     * have the same ContentClaim, which can be significantly more efficient for
     * some implementations of
     * {@link nifi.controller.repository.ContentRepository ContentRepository}
     *
     * @return
     */
    long getContentClaimOffset();
}
