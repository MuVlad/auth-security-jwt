package com.muslimov.vlad.authsecurityjwt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record JwtRequestDto(

    @NotBlank(message = "The name cannot be blank")
    String name,

    @Size(min = 8, max = 255, message = "The password length must be between 8 and 255 characters long")
    @NotBlank(message = "The password can't be blank")
    String password
) {
}