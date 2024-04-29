package com.muslimov.vlad.authsecurityjwt.service.impl;

import com.muslimov.vlad.authsecurityjwt.dto.UserCreateDto;
import com.muslimov.vlad.authsecurityjwt.dto.UserDto;
import com.muslimov.vlad.authsecurityjwt.exception.model.BadRequestException;
import com.muslimov.vlad.authsecurityjwt.exception.model.NotFoundException;
import com.muslimov.vlad.authsecurityjwt.mapper.UserMapper;
import com.muslimov.vlad.authsecurityjwt.model.Role;
import com.muslimov.vlad.authsecurityjwt.model.User;
import com.muslimov.vlad.authsecurityjwt.repository.UserRepository;
import com.muslimov.vlad.authsecurityjwt.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findByName(String name) {
        return userRepository.findFirstByUsername(name).orElseThrow(
            () -> new NotFoundException("User named: " + name + " not found!")
        );
    }

    @Override
    public UserDto findById(Long id) {
        return userMapper.toDto(userRepository.findByIdOrThrow(id));
    }

    @Transactional
    @Override
    public UserDto save(UserCreateDto userCreateDto) {
        if (!Objects.equals(userCreateDto.getPassword(), userCreateDto.getMatchingPassword())) {
            throw new BadRequestException("The passwords don't match");
        }

        if (userRepository.findFirstByEmail(userCreateDto.getEmail()).isPresent()) {
            throw new BadRequestException("Email address already in use");
        }

        var user = userMapper.toEntity(userCreateDto);
        user.setRole(Role.CLIENT);
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        final var saveUser = userRepository.save(user);

        var savedUser = userMapper.toDto(saveUser);
        savedUser.setPassword(userCreateDto.getPassword());
        return savedUser;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByName(username);

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            Collections.singleton(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }
}
