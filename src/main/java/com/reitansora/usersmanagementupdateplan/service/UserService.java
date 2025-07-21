package com.reitansora.usersmanagementupdateplan.service;

import com.reitansora.usersmanagementupdateplan.entity.PlanEntity;
import com.reitansora.usersmanagementupdateplan.entity.UserEntity;
import com.reitansora.usersmanagementupdateplan.model.UserRequest;
import com.reitansora.usersmanagementupdateplan.model.UserResponse;
import com.reitansora.usersmanagementupdateplan.repository.PlanRepository;
import com.reitansora.usersmanagementupdateplan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

/**
 * Service class for managing user-related operations.
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PlanRepository planRepository;


    /**
     * Constructs a UserService with the given UserRepository.
     *
     * @param userRepository the user repository
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserResponse convertToUserResponse(UserEntity newProfile) {
        return UserResponse.builder()
                .userId(newProfile.getUserId())
                .email(newProfile.getEmail())
                .isAccountVerified(newProfile.getIsAccountVerified())
                .planEntity(newProfile.getPlan())
                .profileEntity(newProfile.getProfile())
                .build();
    }

    /**
     * Updates an existing plan's user.
     *
     * @param newPlanName the new plan name
     * @param requestId the user ID
     * @throws ResponseStatusException if the user does not exist
     * @throws ResponseStatusException if the plan is not found
     */
    public UserResponse updateUserPlan(String requestId, String newPlanName) {
        UserEntity userEntity = userRepository.findByUserId(requestId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "User not found with id: " + requestId));

        PlanEntity plan = planRepository.findByName(newPlanName)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "Plan not found: " + newPlanName));

        userEntity.setPlan(plan);
        UserEntity saved = userRepository.save(userEntity);

        return convertToUserResponse(saved);
    }

}
