package com.reitansora.usersmanagementupdateplan.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    @Email(message = "Enter email valid address")
    @NotNull(message = "Email should be not empty")
    private String email;
    @Size(min = 8, message = "Password should be at least 8 characters")
    private String password;
}
