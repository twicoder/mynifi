package org.apache.nifi.components;

import java.util.Objects;

/**
 * <p>
 * Represents a valid value for a {@link PropertyDescriptor}
 * </p>
 */
public class AllowableValue {

    private final String value;
    private final String displayName;
    private final String description;

    /**
     * Constructs a new AllowableValue with the given value and and the same
     * display name and no description.
     *
     * @param value
     */
    public AllowableValue(final String value) {
        this(value, value);
    }

    /**
     * Constructs a new AllowableValue with the given value and display name and
     * no description
     *
     * @param value
     * @param displayName
     *
     * @throws NullPointerException if either argument is null
     */
    public AllowableValue(final String value, final String displayName) {
        this(value, displayName, null);
    }

    /**
     * Constructs a new AllowableValue with the given value, display name, and
     * description
     *
     * @param value
     * @param displayName
     * @param description
     *
     * @throws NullPointerException if identifier or value is null
     */
    public AllowableValue(final String value, final String displayName, final String description) {
        this.value = Objects.requireNonNull(value);
        this.displayName = Objects.requireNonNull(displayName);
        this.description = description;
    }

    /**
     * Returns the value of this AllowableValue
     *
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns a human-readable name for this AllowableValue
     *
     * @return
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns a description for this value, or <code>null</code> if no
     * description was provided
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * <code>this</code> is equal to <code>obj</code> of <code>obj</code> is the
     * same object as <code>this</code> or if <code>obj</code> is an instance of
     * <code>AllowableValue</code> and both have the same value, or if
     * <code>obj</code> is a String and is equal to
     * {@link #getValue() this.getValue()}.
     * @return
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof AllowableValue) {
            final AllowableValue other = (AllowableValue) obj;
            return (this.value.equals(other.getValue()));
        } else if (obj instanceof String) {
            return this.value.equals(obj);
        }

        return false;
    }

    /**
     * Hash Code is based solely off of the value
     * @return
     */
    @Override
    public int hashCode() {
        return 23984731 + 17 * value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}
