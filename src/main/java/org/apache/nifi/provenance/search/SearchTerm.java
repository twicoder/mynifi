package org.apache.nifi.provenance.search;

public interface SearchTerm {

    SearchableField getSearchableField();

    String getValue();
}
