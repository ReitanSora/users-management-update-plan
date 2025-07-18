package com.reitansora.usersmanagementupdateplan.controller;


import com.reitansora.usersmanagementupdateplan.entity.UpdatePlanRequest;
import com.reitansora.usersmanagementupdateplan.model.UserResponse;
import com.reitansora.usersmanagementupdateplan.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping(path = "/plan/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updatePlan(
            @PathVariable("id") String id,
            @RequestBody @Valid UpdatePlanRequest req
    ) {
        return this.userService.updateUserPlan(id, req.getPlanName());
    }

}
