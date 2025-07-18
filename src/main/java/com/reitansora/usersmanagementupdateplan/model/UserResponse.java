package com.reitansora.usersmanagementupdateplan.model;

import com.reitansora.usersmanagementupdateplan.entity.PlanEntity;
import com.reitansora.usersmanagementupdateplan.entity.ProfileEntity;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents a response containing user details.
 */
@Getter
@Builder
public class UserResponse {


    /**
     * The user ID of the user.
     */
    private final String userId;

    /**
     * The email of the user.
     */
    private final String email;

    /**
     * The user account verification state.
     */
    private Boolean isAccountVerified;
    private PlanEntity planEntity;
    private ProfileEntity profileEntity;
}
