package com.reitansora.usersmanagementupdateplan.security;

import com.reitansora.usersmanagementupdateplan.entity.PlanEntity;
import com.reitansora.usersmanagementupdateplan.entity.ProfileEntity;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

/**
 * Represents the authenticated user principal.
 */
@Getter
@Builder
public class UserPrincipal implements UserDetails {

    private final Long id;
    private final String userId;
    private final String email;
    private final String password;
    private final Boolean isAccountVerified;
    private final String verifyOtp;
    private final Long verifyOtpExpireAt;
    private final String resetOtp;
    private final Long resetOtpExpireAt;
    private PlanEntity plan;
    private ProfileEntity profile;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;

    /**
     * Returns the authorities granted to the user.
     *
     * @return a collection of granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return "";
    }

    /**
     * Indicates whether the user's account has expired.
     *
     * @return true if the account is non-expired, false otherwise
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked.
     *
     * @return true if the account is non-locked, false otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) has expired.
     *
     * @return true if the credentials are non-expired, false otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled.
     *
     * @return true if the user is enabled, false otherwise
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
