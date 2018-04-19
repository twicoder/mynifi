package org.apache.nifi.authorization;

/**
 *
 */
public interface AuthorityProviderLookup {

    AuthorityProvider getAuthorityProvider(String identifier);
}
