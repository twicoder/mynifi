package org.apache.nifi.search;

import java.util.Collection;

/**
 *
 */
public interface Searchable {

    Collection<SearchResult> search(SearchContext context);
}
