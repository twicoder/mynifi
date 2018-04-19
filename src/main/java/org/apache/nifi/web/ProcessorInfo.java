package org.apache.nifi.web;

import java.util.Collection;
import java.util.Map;

/**
 *
 */
public class ProcessorInfo {

    private final String id;
    private final String name;
    private final String state;
    private final String annotationData;
    private final Map<String, String> properties;
    private final Collection<String> validationErrors;

    private ProcessorInfo(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.state = builder.state;
        this.annotationData = builder.annotationData;
        this.properties = builder.properties;
        this.validationErrors = builder.validationErrors;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getAnnotationData() {
        return annotationData;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public Collection<String> getValidationErrors() {
        return validationErrors;
    }

    public static final class Builder {

        private String id;
        private String name;
        private String state;
        private String annotationData;
        private Map<String, String> properties;
        private Collection<String> validationErrors;

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder state(final String state) {
            this.state = state;
            return this;
        }

        public Builder annotationData(final String annotationData) {
            this.annotationData = annotationData;
            return this;
        }

        public Builder properties(final Map<String, String> properties) {
            this.properties = properties;
            return this;
        }

        public Builder validateErrors(final Collection<String> validationErrors) {
            this.validationErrors = validationErrors;
            return this;
        }

        public ProcessorInfo build() {
            return new ProcessorInfo(this);
        }
    }
}
