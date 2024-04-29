package com.muslimov.vlad.authsecurityjwt.controller;

import com.muslimov.vlad.authsecurityjwt.dto.JwtRequestDto;
import com.muslimov.vlad.authsecurityjwt.dto.JwtResponseDto;
import com.muslimov.vlad.authsecurityjwt.dto.UserCreateDto;
import com.muslimov.vlad.authsecurityjwt.dto.UserDto;
import com.muslimov.vlad.authsecurityjwt.service.AuthService;
import com.muslimov.vlad.authsecurityjwt.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "AuthController", description = "Controller for registration and authorization")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/sign-up")
    @Operation(summary = "New user registration")
    public HttpEntity<UserDto> createNewUser(@RequestBody @Valid UserCreateDto userCreateDto) {
        return ResponseEntity.ok(userService.save(userCreateDto));
    }

    @PostMapping("/sign-in")
    @Operation(summary = "User Authentication")
    public HttpEntity<JwtResponseDto> createAuthToken(@RequestBody @Valid JwtRequestDto jwtRequestDto) {
        return ResponseEntity.ok(authService.createAuthToken(jwtRequestDto));
    }
}
