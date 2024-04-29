package com.muslimov.vlad.authsecurityjwt.controller;

import com.muslimov.vlad.authsecurityjwt.support.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminControllerTest extends BaseIntegrationTest {

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminsData() throws Exception {
        mockMvc.perform(get("/api/v1/admins"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"))
            .andExpect(content().string("Admins Data"));
    }
}