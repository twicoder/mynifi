package org.apache.nifi.expression;

public interface AttributeValueDecorator {

    /**
     * Decorates the value of a FlowFile Attribute or System/JVM property in
     * some way
     *
     * @param attributeValue
     * @return
     */
    String decorate(String attributeValue);
}
