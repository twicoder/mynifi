package org.apache.nifi.web;

/**
 *
 */
public class ProcessorConfigurationAction {

    private final String processorId;
    private final String processorName;
    private final String processorType;
    private final String name;
    private final String previousValue;
    private final String value;

    private ProcessorConfigurationAction(final Builder builder) {
        this.processorId = builder.processorId;
        this.processorName = builder.processorName;
        this.processorType = builder.processorType;
        this.name = builder.name;
        this.previousValue = builder.previousValue;
        this.value = builder.value;
    }

    /**
     * Gets the id of the processor.
     *
     * @return
     */
    public String getProcessorId() {
        return processorId;
    }

    /**
     * Gets the name of the processor being modified.
     *
     * @return
     */
    public String getProcessorName() {
        return processorName;
    }

    /**
     * Gets the type of the processor being modified.
     *
     * @return
     */
    public String getProcessorType() {
        return processorType;
    }

    /**
     * Gets the name of the field, property, etc that has been modified.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the previous value.
     *
     * @return
     */
    public String getPreviousValue() {
        return previousValue;
    }

    /**
     * Gets the new value.
     *
     * @return
     */
    public String getValue() {
        return value;
    }

    public static class Builder {

        private String processorId;
        private String processorName;
        private String processorType;
        private String name;
        private String previousValue;
        private String value;

        public Builder processorId(final String processorId) {
            this.processorId = processorId;
            return this;
        }

        public Builder processorName(final String processorName) {
            this.processorName = processorName;
            return this;
        }

        public Builder processorType(final String processorType) {
            this.processorType = processorType;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder previousValue(final String previousValue) {
            this.previousValue = previousValue;
            return this;
        }

        public Builder value(final String value) {
            this.value = value;
            return this;
        }

        public ProcessorConfigurationAction build() {
            return new ProcessorConfigurationAction(this);
        }
    }
}
