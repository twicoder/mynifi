package org.apache.nifi.components;

import java.util.Objects;

/**
 *
 * Immutable - thread safe
 *
 * @author unattributed
 */
public class ValidationResult {

    private final String subject;
    private final String input;
    private final String explanation;
    private final boolean valid;

    private ValidationResult(final Builder builder) {
        this.subject = builder.subject;
        this.input = builder.input;
        this.explanation = builder.explanation;
        this.valid = builder.valid;
    }

    /**
     * @return true if current result is valid; false otherwise
     */
    public boolean isValid() {
        return this.valid;
    }

    /**
     * @return this input value that was tested for validity
     */
    public String getInput() {
        return this.input;
    }

    /**
     * @return an explanation of the validation result
     */
    public String getExplanation() {
        return this.explanation;
    }

    /**
     * @return the item being validated
     */
    public String getSubject() {
        return this.subject;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.subject);
        hash = 79 * hash + Objects.hashCode(this.input);
        hash = 79 * hash + Objects.hashCode(this.explanation);
        hash = 79 * hash + (this.valid ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ValidationResult other = (ValidationResult) obj;
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        if (!Objects.equals(this.input, other.input)) {
            return false;
        }
        if (!Objects.equals(this.explanation, other.explanation)) {
            return false;
        }
        if (this.valid != other.valid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (input == null) {
            return String.format("'%s' is %s because %s", subject, (valid ? "valid" : "invalid"), explanation);
        } else {
            return String.format("'%s' validated against '%s' is %s because %s", subject, input, (valid ? "valid" : "invalid"), explanation);
        }
    }

    public static final class Builder {

        private boolean valid = false;
        private String input = null;
        private String explanation = "";
        private String subject = "";

        /**
         * Defaults to false
         *
         * @param valid
         * @return
         */
        public Builder valid(final boolean valid) {
            this.valid = valid;
            return this;
        }

        /**
         * Defaults to empty string
         *
         * @param input
         * @return
         */
        public Builder input(final String input) {
            if (null != input) {
                this.input = input;
            }
            return this;
        }

        /**
         * Defaults to empty string
         *
         * @param explanation
         * @return
         */
        public Builder explanation(final String explanation) {
            if (null != explanation) {
                this.explanation = explanation;
            }
            return this;
        }

        /**
         * Defaults to empty string
         *
         * @param subject
         * @return
         */
        public Builder subject(final String subject) {
            if (null != subject) {
                this.subject = subject;
            }
            return this;
        }

        public ValidationResult build() {
            return new ValidationResult(this);
        }
    }
}
