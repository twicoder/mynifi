package org.apache.nifi.provenance.search;

import java.util.*;

public class Query {

    private final String identifier;
    private final List<SearchTerm> searchTerms = new ArrayList<>();
    private Date startDate;
    private Date endDate;
    private String minFileSize;
    private String maxFileSize;
    private int maxResults = 1000;

    public Query(final String identifier) {
        this.identifier = Objects.requireNonNull(identifier);
    }

    public String getIdentifier() {
        return identifier;
    }

    public void addSearchTerm(final SearchTerm searchTerm) {
        searchTerms.add(searchTerm);
    }

    public List<SearchTerm> getSearchTerms() {
        return Collections.unmodifiableList(searchTerms);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public void setMinFileSize(final String fileSize) {
        this.minFileSize = fileSize;
    }

    public String getMinFileSize() {
        return minFileSize;
    }

    public void setMaxFileSize(final String fileSize) {
        this.maxFileSize = fileSize;
    }

    public String getMaxFileSize() {
        return maxFileSize;
    }

    @Override
    public String toString() {
        return "Query[ " + searchTerms + " ]";
    }
}