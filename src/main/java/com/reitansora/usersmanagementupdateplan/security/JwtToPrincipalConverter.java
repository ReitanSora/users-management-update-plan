package com.reitansora.usersmanagementupdateplan.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * Component responsible for converting a decoded JWT to a UserPrincipal.
 */
@Component
public class JwtToPrincipalConverter {

    /**
     * Converts a decoded JWT to a UserPrincipal.
     *
     * @param decodedJWT the decoded JWT
     * @return the UserPrincipal containing user information
     */
    public UserPrincipal convert(DecodedJWT decodedJWT) {
        return UserPrincipal.builder()
                .userId(String.valueOf(decodedJWT.getSubject()))
                .email(decodedJWT.getClaim("email").asString())
                .createdAt(getCreatedAtTimestamp(decodedJWT))
                .build();
    }

    private Timestamp getCreatedAtTimestamp(DecodedJWT decodedJWT) {
        String createdAtStr = decodedJWT.getClaim("createdAt").asString();
        if (createdAtStr == null || createdAtStr.isEmpty()) {
            // If createdAt is not present, use current timestamp
            return new Timestamp(Instant.now().toEpochMilli());
        }
        return Timestamp.valueOf(createdAtStr);
    }

    /**
     * Extracts authorities from the decoded JWT.
     *
     * @param decodedJWT the decoded JWT
     * @return a list of SimpleGrantedAuthority
     */
    private List<SimpleGrantedAuthority> getAuthorities(DecodedJWT decodedJWT) {
        var claim = decodedJWT.getClaim("authorities");
        if (claim.isNull() || claim.isMissing()) return List.of();
        return claim.asList(SimpleGrantedAuthority.class);
    }
}
