package org.apache.nifi.provenance.search;

/**
 * A SearchableField represents a field in a Provenance Event that can be
 * searched
 */
public interface SearchableField {

    /**
     * Returns the identifier that is used to refer to this field
     *
     * @return
     */
    String getIdentifier();

    /**
     * Returns the name of the field that is used when searching the repository.
     *
     * @return
     */
    String getSearchableFieldName();

    /**
     * Returns the "friendly" name or "display name" of the field, which may be
     * more human-readable than the searchable field name
     *
     * @return
     */
    String getFriendlyName();

    /**
     * Returns the type of the data stored in this field
     *
     * @return
     */
    SearchableFieldType getFieldType();

    /**
     * Returns <code>true</code> if this field represents a FlowFile attribute,
     * <code>false</code> if the field represents a Provenance Event detail,
     * such as Source System URI
     *
     * @return
     */
    boolean isAttribute();
}
