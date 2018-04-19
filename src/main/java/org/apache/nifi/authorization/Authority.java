package org.apache.nifi.authorization;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Authorities that can be assigned to NiFi users.
 */
public enum Authority {

    ROLE_MONITOR,
    ROLE_DFM,
    ROLE_ADMIN,
    ROLE_PROVENANCE,
    ROLE_PROXY,
    ROLE_NIFI;

    /**
     * Returns the matching role or null if the specified role does not match
     * any roles.
     *
     * @param rawAuthority
     * @return
     */
    public static Authority valueOfAuthority(String rawAuthority) {
        Authority desiredAuthority = null;

        for (Authority authority : values()) {
            if (authority.toString().equals(rawAuthority)) {
                desiredAuthority = authority;
                break;
            }
        }

        return desiredAuthority;
    }

    /**
     * Gets the string value of each authority.
     *
     * @return
     */
    public static Set<String> getRawAuthorities() {
        Set<String> authorities = new LinkedHashSet<>();
        for (Authority authority : values()) {
            authorities.add(authority.toString());
        }
        return authorities;
    }

    public static Set<String> convertAuthorities(Set<Authority> authorities) {
        if (authorities == null) {
            throw new IllegalArgumentException("No authorities have been specified.");
        }

        // convert the set
        Set<String> rawAuthorities = new HashSet<>(authorities.size());
        for (Authority authority : authorities) {
            rawAuthorities.add(authority.toString());
        }
        return rawAuthorities;
    }

    public static EnumSet<Authority> convertRawAuthorities(Set<String> rawAuthorities) {
        if (rawAuthorities == null) {
            throw new IllegalArgumentException("No authorities have been specified.");
        }

        // convert the set
        EnumSet<Authority> authorities = EnumSet.noneOf(Authority.class);
        for (String rawAuthority : rawAuthorities) {
            Authority authority = Authority.valueOfAuthority(rawAuthority);
            if (authority != null) {
                authorities.add(authority);
            }
        }
        return authorities;
    }
}
