package org.apache.nifi.web;

import java.io.Serializable;

/**
 * A model object representing a revision. Equality is defined as either a
 * matching version number or matching non-empty client IDs.
 *
 * @author unattributed
 * @Immutable
 * @Threadsafe
 */
public class Revision implements Serializable {

    /**
     * the version number
     */
    private final Long version;

    /**
     * the client ID
     */
    private final String clientId;

    public Revision(Long revision, String clientId) {
        this.version = revision;
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
    }

    public Long getVersion() {
        return version;
    }

    /**
     * A factory method for creating a new Revision instance whose version is
     * this instance's version plus 1.
     *
     * @return an updated revision
     */
    public Revision increment() {
        final long incrementedVersion;
        if (version == null) {
            incrementedVersion = 0;
        } else {
            incrementedVersion = version + 1;
        }
        return new Revision(incrementedVersion, clientId);
    }

    /**
     * A factory method for creating a new Revision instance whose version is
     * this instance's version plus 1 and whose client ID is the given client
     * ID.
     *
     * @param clientId the client ID
     * @return an updated revision
     */
    public Revision increment(String clientId) {
        return new Revision(increment().getVersion(), clientId);
    }

    @Override
    public boolean equals(final Object obj) {

        if ((obj instanceof Revision) == false) {
            return false;
        }

        Revision thatRevision = (Revision) obj;
        if (this.version != null && this.version.equals(thatRevision.version)) {
            return true;
        } else {
            return clientId != null && !clientId.trim().isEmpty() && clientId.equals(thatRevision.getClientId());
        }

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.version != null ? this.version.hashCode() : 0);
        hash = 59 * hash + (this.clientId != null ? this.clientId.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "[" + version + ", " + clientId + ']';
    }
}
