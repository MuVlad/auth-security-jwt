package com.muslimov.vlad.authsecurityjwt.controller;

import com.muslimov.vlad.authsecurityjwt.dto.UserDto;
import com.muslimov.vlad.authsecurityjwt.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "UserController", description = "Controller for user operation")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @Operation(summary = "Retrieving a user by identifier")
    public HttpEntity<UserDto> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleting a user")
    public HttpEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
