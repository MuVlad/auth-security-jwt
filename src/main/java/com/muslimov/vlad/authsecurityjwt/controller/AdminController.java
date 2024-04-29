package com.muslimov.vlad.authsecurityjwt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admins")
@Tag(name = "AdminController", description = "Controller for admin operation")
public class AdminController {

    @GetMapping
    @Operation(summary = "Retrieving admin data")
    public HttpEntity<?> adminsData() {
        return ResponseEntity.ok().body("Admins Data");
    }

}
