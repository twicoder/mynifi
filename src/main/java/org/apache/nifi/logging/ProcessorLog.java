package org.apache.nifi.logging;

public interface ProcessorLog {

    void warn(String msg, Throwable t);

    void warn(String msg, Object[] os);

    void warn(String msg, Object[] os, Throwable t);

    void warn(String msg);

    void trace(String msg, Throwable t);

    void trace(String msg, Object[] os);

    void trace(String msg);

    void trace(String msg, Object[] os, Throwable t);

    boolean isWarnEnabled();

    boolean isTraceEnabled();

    boolean isInfoEnabled();

    boolean isErrorEnabled();

    boolean isDebugEnabled();

    void info(String msg, Throwable t);

    void info(String msg, Object[] os);

    void info(String msg);

    void info(String msg, Object[] os, Throwable t);

    String getName();

    void error(String msg, Throwable t);

    void error(String msg, Object[] os);

    void error(String msg);

    void error(String msg, Object[] os, Throwable t);

    void debug(String msg, Throwable t);

    void debug(String msg, Object[] os);

    void debug(String msg, Object[] os, Throwable t);

    void debug(String msg);

}
