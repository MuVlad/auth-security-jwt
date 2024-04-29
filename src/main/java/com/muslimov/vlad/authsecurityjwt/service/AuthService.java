package com.muslimov.vlad.authsecurityjwt.service;

import com.muslimov.vlad.authsecurityjwt.dto.JwtRequestDto;
import com.muslimov.vlad.authsecurityjwt.dto.JwtResponseDto;
import com.muslimov.vlad.authsecurityjwt.exception.model.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public JwtResponseDto createAuthToken(JwtRequestDto jwtRequestDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                jwtRequestDto.name(),
                jwtRequestDto.password()
            ));

        } catch (BadCredentialsException ex) {
            throw new BadRequestException("Invalid login or password!");
        }

        UserDetails userDetails = userService.loadUserByUsername(jwtRequestDto.name());

        String token = tokenService.generateToken(userDetails);
        return new JwtResponseDto(token);
    }
}
