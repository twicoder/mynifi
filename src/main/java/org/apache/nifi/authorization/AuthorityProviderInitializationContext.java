package org.apache.nifi.authorization;

/**
 *
 */
public interface AuthorityProviderInitializationContext {

    public String getIdentifier();

    public AuthorityProviderLookup getAuthorityProviderLookup();
}
