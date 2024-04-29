package com.muslimov.vlad.authsecurityjwt.service;

import com.muslimov.vlad.authsecurityjwt.dto.JwtRequestDto;
import com.muslimov.vlad.authsecurityjwt.dto.JwtResponseDto;
import com.muslimov.vlad.authsecurityjwt.exception.model.BadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = AuthService.class)
class AuthServiceTest {

    @MockBean
    private UserService userService;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @Test
    public void CreateAuthTokenWhenValidCredentials() {
        JwtRequestDto jwtRequestDto = new JwtRequestDto("username", "password");
        UserDetails userDetails = mock(UserDetails.class);
        String token = "generatedToken";
        JwtResponseDto expectedResponseDto = new JwtResponseDto(token);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(userService.loadUserByUsername(jwtRequestDto.name())).thenReturn(userDetails);
        when(tokenService.generateToken(userDetails)).thenReturn(token);

        JwtResponseDto actualResponseDto = authService.createAuthToken(jwtRequestDto);

        assertEquals(expectedResponseDto, actualResponseDto);
    }

    @Test
    public void CreateAuthTokenWhenInvalidCredentials() {
        JwtRequestDto jwtRequestDto = new JwtRequestDto("username", "password");

        when(authenticationManager.authenticate(any()))
            .thenThrow(BadCredentialsException.class);

        assertThrows(BadRequestException.class, () -> authService.createAuthToken(jwtRequestDto));
    }
}
