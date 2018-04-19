package org.apache.nifi.reporting;

import java.util.regex.Pattern;

/**
 *
 */
public class BulletinQuery {

    private final Pattern sourceIdPattern;
    private final Pattern groupIdPattern;
    private final Pattern namePattern;
    private final Pattern messagePattern;
    private final Long after;
    private final Integer limit;

    private BulletinQuery(final Builder builder) {
        this.sourceIdPattern = builder.sourceIdPattern == null ? null : Pattern.compile(builder.sourceIdPattern);
        this.groupIdPattern = builder.groupIdPattern == null ? null : Pattern.compile(builder.groupIdPattern);
        this.namePattern = builder.namePattern == null ? null : Pattern.compile(builder.namePattern);
        this.messagePattern = builder.messagePattern == null ? null : Pattern.compile(builder.messagePattern);
        this.after = builder.after;
        this.limit = builder.limit;
    }

    public Pattern getSourceIdPattern() {
        return sourceIdPattern;
    }

    public Pattern getGroupIdPattern() {
        return groupIdPattern;
    }

    public Pattern getNamePattern() {
        return namePattern;
    }

    public Pattern getMessagePattern() {
        return messagePattern;
    }

    public Long getAfter() {
        return after;
    }

    public Integer getLimit() {
        return limit;
    }

    public static class Builder {

        private String sourceIdPattern;
        private String groupIdPattern;
        private String namePattern;
        private String messagePattern;
        private Long after;
        private Integer limit;

        public Builder after(Long after) {
            this.after = after;
            return this;
        }

        public Builder groupIdMatches(String groupId) {
            this.groupIdPattern = groupId;
            return this;
        }

        public Builder sourceIdMatches(String sourceId) {
            this.sourceIdPattern = sourceId;
            return this;
        }

        public Builder nameMatches(String name) {
            this.namePattern = name;
            return this;
        }

        public Builder messageMatches(String message) {
            this.messagePattern = message;
            return this;
        }

        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public BulletinQuery build() {
            return new BulletinQuery(this);
        }
    }
}
