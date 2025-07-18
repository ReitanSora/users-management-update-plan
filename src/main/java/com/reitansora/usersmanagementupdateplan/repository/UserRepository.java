package com.reitansora.usersmanagementupdateplan.repository;

import com.reitansora.usersmanagementupdateplan.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Finds a user entity by email.
     *
     * @param email the email of the user to find
     * @return an Optional containing the found UserEntity, or empty if not found
     */
    Optional<UserEntity> findByEmail(String email);

    /**
     * Finds if a user already exists.
     *
     * @param email the email of the user to find
     * @return a Boolean containing if an user exists with the provided email
     */
    Boolean existsByEmail(String email);

    Optional<UserEntity> findByUserId(String userId);

    /**
     * Checks if a user exists by their userId.
     *
     * @param userId the userId to check
     * @return true if user exists, false otherwise
     */
    Boolean existsByUserId(String userId);
}
