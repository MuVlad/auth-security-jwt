package com.muslimov.vlad.authsecurityjwt.controller;

import com.muslimov.vlad.authsecurityjwt.dto.UserCreateDto;
import com.muslimov.vlad.authsecurityjwt.support.BaseIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AuthControllerTest extends BaseIntegrationTest {


    @Test
    @DisplayName("User registration")
    void createNewUser() throws Exception {
        UserCreateDto userCreateDto = new UserCreateDto(
            "Test User",
            "test@mail.com",
            "pass",
            "pass"
        );

        mockMvc.perform(post("/api/v1/auth/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userCreateDto))
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(3))
            .andExpect(jsonPath("$.username").value(userCreateDto.getUsername()))
            .andExpect(jsonPath("$.email").value(userCreateDto.getEmail()))
            .andExpect(jsonPath("$.password").value(userCreateDto.getPassword()));
    }

}