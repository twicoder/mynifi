package org.apache.nifi.web;

/**
 * Context configuration for methods invoked from the NiFiWebContext.
 */
public interface NiFiWebContextConfig {

    /**
     * The request protocol scheme (http or https). When scheme is https, the
     * X509Certificate can be used for subsequent remote requests.
     *
     * @return the protocol scheme
     */
    String getScheme();

    /**
     * @return the processor ID
     */
    String getProcessorId();

    /**
     * @return the revision
     */
    Revision getRevision();

    /**
     * Returns the proxied entities chain. The format of the chain is as
     * follows:
     *
     * <code>
     * &lt;CN=original-proxied-entity&gt;&lt;CN=first-proxy&gt;&lt;CN=second-proxy&gt;...
     * </code>
     *
     * @return the proxied entities chain or null if no chain
     */
    String getProxiedEntitiesChain();

}

