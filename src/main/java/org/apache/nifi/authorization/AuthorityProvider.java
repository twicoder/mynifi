package org.apache.nifi.authorization;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.nifi.authorization.exception.AuthorityAccessException;
import org.apache.nifi.authorization.exception.IdentityAlreadyExistsException;
import org.apache.nifi.authorization.exception.ProviderCreationException;
import org.apache.nifi.authorization.exception.ProviderDestructionException;
import org.apache.nifi.authorization.exception.UnknownIdentityException;

/**
 * This class allows clients to retrieve the authorities for a given DN.
 */
public interface AuthorityProvider {

    /**
     * Returns whether the user with the specified DN is known to this authority
     * provider. It is not necessary for the user to have any authorities.
     *
     * @param dn
     * @return
     */
    boolean doesDnExist(String dn) throws AuthorityAccessException;

    /**
     * Get the authorities for the specified user. If the specified user exists
     * but does not have any authorities, an empty set should be returned.
     *
     * @param dn
     * @return
     * @throws UnknownIdentityException
     * @throws AuthorityAccessException
     */
    Set<Authority> getAuthorities(String dn) throws UnknownIdentityException, AuthorityAccessException;

    /**
     * Sets the specified authorities for the specified user.
     *
     * @param dn
     * @param authorities
     * @throws UnknownIdentityException
     * @throws AuthorityAccessException
     */
    void setAuthorities(String dn, Set<Authority> authorities) throws UnknownIdentityException, AuthorityAccessException;

    /**
     * Gets the users for the specified authority.
     *
     * @param authority
     * @return
     * @throws AuthorityAccessException
     */
    Set<String> getUsers(Authority authority) throws AuthorityAccessException;

    /**
     * Revokes the specified user. Its up to the implementor to determine the
     * semantics of revocation.
     *
     * @param dn
     * @throws UnknownIdentityException
     * @throws AuthorityAccessException
     */
    void revokeUser(String dn) throws UnknownIdentityException, AuthorityAccessException;

    /**
     * Add the specified user.
     *
     * @param dn
     * @param group Optional
     * @throws IdentityAlreadyExistsException
     * @throws AuthorityAccessException
     */
    void addUser(String dn, String group) throws IdentityAlreadyExistsException, AuthorityAccessException;

    /**
     * Gets the group for the specified user. Return null if the user does not
     * belong to a group.
     *
     * @param dn
     * @return
     * @throws UnknownIdentityException
     * @throws AuthorityAccessException
     */
    String getGroupForUser(String dn) throws UnknownIdentityException, AuthorityAccessException;

    /**
     * Revokes all users for a specified group. Its up to the implementor to
     * determine the semantics of revocation.
     *
     * @param group
     * @throws AuthorityAccessException
     */
    void revokeGroup(String group) throws UnknownIdentityException, AuthorityAccessException;

    /**
     * Adds the specified users to the specified group.
     *
     * @param dn
     * @param group
     * @throws AuthorityAccessException
     */
    void setUsersGroup(Set<String> dn, String group) throws UnknownIdentityException, AuthorityAccessException;

    /**
     * Ungroups the specified user.
     *
     * @param dn
     * @throws UnknownIdentityException
     * @throws AuthorityAccessException
     */
    void ungroupUser(String dn) throws UnknownIdentityException, AuthorityAccessException;

    /**
     * Ungroups the specified group. Since the semantics of revocation is up to
     * the implementor, this method should do nothing if the specified group
     * does not exist. If an admin revoked this group before calling ungroup, it
     * may or may not exist.
     *
     * @param group
     * @throws AuthorityAccessException
     */
    void ungroup(String group) throws AuthorityAccessException;

    /**
     * Determines whether the user in the specified dnChain should be able to
     * download the content for the flowfile with the specified attributes.
     *
     * The first dn in the chain is the end user that the request was issued on
     * behalf of. The subsequent dn's in the chain represent entities proxying
     * the user's request with the last being the proxy that sent the current
     * request.
     *
     * @param dnChain
     * @param attributes
     * @return
     * @throws UnknownIdentityException
     * @throws AuthorityAccessException
     */
    DownloadAuthorization authorizeDownload(List<String> dnChain, Map<String, String> attributes) throws UnknownIdentityException, AuthorityAccessException;

    /**
     * Called immediately after instance creation for implementers to perform
     * additional setup
     *
     * @param initializationContext
     */
    void initialize(AuthorityProviderInitializationContext initializationContext) throws ProviderCreationException;

    /**
     * Called to configure the AuthorityProvider.
     *
     * @param configurationContext
     * @throws ProviderCreationException
     */
    void onConfigured(AuthorityProviderConfigurationContext configurationContext) throws ProviderCreationException;

    /**
     * Called immediately before instance destruction for implementers to
     * release resources.
     *
     * @throws ProviderDestructionException If pre-destruction fails.
     */
    void preDestruction() throws ProviderDestructionException;
}