package org.apache.nifi.search;

/**
 *
 */
public class SearchResult {

    private final String label;
    private final String match;

    private SearchResult(final Builder builder) {
        this.label = builder.label;
        this.match = builder.match;
    }

    /**
     * Get the label for this search result.
     *
     * @return
     */
    public String getLabel() {
        return label;
    }

    /**
     * Get the matching string for this search result.
     *
     * @return
     */
    public String getMatch() {
        return match;
    }

    public static final class Builder {

        private String label;
        private String match;

        /**
         * Set the label for the search result.
         *
         * @param label
         * @return
         */
        public Builder label(final String label) {
            this.label = label;
            return this;
        }

        /**
         * Set the matching string for the search result.
         *
         * @param match
         * @return
         */
        public Builder match(final String match) {
            this.match = match;
            return this;
        }

        public SearchResult build() {
            return new SearchResult(this);
        }

    }
}
