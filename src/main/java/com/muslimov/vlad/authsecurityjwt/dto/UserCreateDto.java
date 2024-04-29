package com.muslimov.vlad.authsecurityjwt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {

    @NotBlank(message = "The name cannot be blank")
    private String username;

    @Size(min = 5, max = 255, message = "The email address must contain between 5 and 255 characters")
    @NotBlank(message = "The email address cannot be blank")
    private String email;

    @Size(min = 8, max = 255, message = "The password length must be between 8 and 255 characters long")
    @NotBlank(message = "The password can't be blank")
    private String password;

    @Size(min = 8, max = 255, message = "The password length must be between 8 and 255 characters long")
    @NotBlank(message = "The password can't be blank")
    private String matchingPassword;
}
