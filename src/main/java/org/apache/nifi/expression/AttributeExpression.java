package org.apache.nifi.expression;

import org.apache.nifi.flowfile.FlowFile;
import org.apache.nifi.processor.exception.ProcessException;

public interface AttributeExpression {

    /**
     * Evaluates the expression without providing any FlowFile Attributes. This
     * will evaluate the expression based only on System Properties and JVM
     * Environment properties
     *
     * @return
     * @throws ProcessException
     */
    String evaluate() throws ProcessException;

    /**
     * Evaluates the expression without providing any FlowFile Attributes. This
     * will evaluate the expression based only on System Properties and JVM
     * Environment properties but allows the values to be decorated
     *
     * @param decorator
     * @return
     * @throws ProcessException
     */
    String evaluate(AttributeValueDecorator decorator) throws ProcessException;

    /**
     * Evaluates the expression, providing access to the attributes, file size,
     * id, etc. of the given FlowFile, as well as System Properties and JVM
     * Environment properties
     *
     * @param flowFile
     * @return
     * @throws ProcessException
     */
    String evaluate(FlowFile flowFile) throws ProcessException;

    /**
     * Evaluates the expression, providing access to the attributes, file size,
     * id, etc. of the given FlowFile, as well as System Properties and JVM
     * Environment properties and allows the values to be decorated
     *
     * @param flowFile
     * @param decorator
     * @return
     * @throws ProcessException
     */
    String evaluate(FlowFile flowFile, AttributeValueDecorator decorator) throws ProcessException;

    /**
     * Returns the type that is returned by the Expression
     *
     * @return
     */
    ResultType getResultType();

    public static enum ResultType {

        STRING, BOOLEAN, NUMBER, DATE;
    }
}
