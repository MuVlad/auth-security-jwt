package com.muslimov.vlad.authsecurityjwt.controller;

import com.muslimov.vlad.authsecurityjwt.model.Role;
import com.muslimov.vlad.authsecurityjwt.model.User;
import com.muslimov.vlad.authsecurityjwt.support.BaseIntegrationTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest extends BaseIntegrationTest {

    @AfterEach
    public void resetDB() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Getting a user by id")
    @WithMockUser
    void getUser() throws Exception {
        User user = getSave();

        assertNotNull(user);

        mockMvc.perform(get("/api/v1/users/{id}", user.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(user.getId()))
            .andExpect(jsonPath("$.username").value(user.getUsername()))
            .andExpect(jsonPath("$.email").value(user.getEmail()))
            .andExpect(jsonPath("$.password").value(user.getPassword()))
            .andDo(print());
    }

    @Test
    @DisplayName("Deletion a user by id")
    @WithMockUser
    void deleteUser() throws Exception {
        User user = getSave();

        mockMvc.perform(delete("/api/v1/users/{id}", user.getId()))
            .andExpect(status().isNoContent());
        assertFalse(userRepository.findById(user.getId()).isPresent());
    }

    protected User getSave() {
        return userRepository.save(
            new User(1l, "Vasya", "vasya@mail.com", "pass", Role.CLIENT)
        );
    }
}