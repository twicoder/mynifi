package org.apache.nifi.reporting;

import java.util.Date;

/**
 * A Bulletin is a construct that represents a message that is to be displayed
 * to users to notify of a specific (usually fleeting) event.
 */
public abstract class Bulletin implements Comparable<Bulletin> {

    private final Date timestamp;
    private final long id;
    private String nodeAddress;
    private String level;
    private String category;
    private String message;

    private String groupId;
    private String sourceId;
    private String sourceName;

    protected Bulletin(final long id) {
        this.timestamp = new Date();
        this.id = id;
    }

    public String getNodeAddress() {
        return nodeAddress;
    }

    public void setNodeAddress(String nodeAddress) {
        this.nodeAddress = nodeAddress;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    @Override
    public String toString() {
        return "Bulletin{" + "id=" + id + ", message=" + message + ", sourceName=" + sourceName + '}';
    }

    @Override
    public int compareTo(Bulletin b) {
        if (b == null) {
            return -1;
        }

        return -Long.compare(getId(), b.getId());
    }
}
