package org.apache.nifi.provenance.search;

public class SearchTerms {

    public static SearchTerm newSearchTerm(final SearchableField field, final String value) {
        return new SearchTerm() {
            @Override
            public SearchableField getSearchableField() {
                return field;
            }

            @Override
            public String getValue() {
                return value;
            }

            @Override
            public String toString() {
                return getValue();
            }
        };
    }
}
