package com.muslimov.vlad.authsecurityjwt.service.impl;

import com.muslimov.vlad.authsecurityjwt.dto.UserCreateDto;
import com.muslimov.vlad.authsecurityjwt.exception.model.BadRequestException;
import com.muslimov.vlad.authsecurityjwt.exception.model.NotFoundException;
import com.muslimov.vlad.authsecurityjwt.mapper.UserMapper;
import com.muslimov.vlad.authsecurityjwt.model.User;
import com.muslimov.vlad.authsecurityjwt.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = UserServiceImpl.class)
class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceImpl userService;


    @Test
    void findByNameWhenUserExist() {

        String username = "username";
        User expected = new User();
        expected.setUsername(username);

        when(userRepository.findFirstByUsername(username))
            .thenReturn(Optional.of(expected));

        final User actual = userService.findByName(expected.getUsername());

        assertEquals(expected, actual);
        verify(userRepository).findFirstByUsername(username);
    }

    @Test
    void findByNameWhenUserNotExist() {
        String username = "username";

        when(userRepository.findFirstByUsername(username))
            .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.findByName(username));
    }

    @Test
    public void saveUserWhenPasswordsNotMatching() {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setPassword("password");
        userCreateDto.setMatchingPassword("differentPassword");

        assertThrows(BadRequestException.class, () -> userService.save(userCreateDto));
    }

    @Test
    public void saveUserWhenEmailAlreadyExists() {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setEmail("existing@example.com");

        when(userRepository.findFirstByEmail(userCreateDto.getEmail())).thenReturn(Optional.of(new User()));

        assertThrows(BadRequestException.class, () -> userService.save(userCreateDto));
        verify(userRepository, times(1)).findFirstByEmail(userCreateDto.getEmail());
    }
}