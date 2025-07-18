package com.reitansora.usersmanagementupdateplan.security;

import com.reitansora.usersmanagementupdateplan.entity.UserEntity;
import com.reitansora.usersmanagementupdateplan.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Custom implementation of UserDetailsService to load user-specific data.
 */
@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Loads the user details by username (email).
     *
     * @param email the email of the user to load
     * @return UserDetails containing user information
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Find user in your repository
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow();

        // Return UserPrincipal instead of User
        return UserPrincipal.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .password(user.getPassword())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
