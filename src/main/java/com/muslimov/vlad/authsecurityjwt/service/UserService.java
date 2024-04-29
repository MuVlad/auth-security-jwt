package com.muslimov.vlad.authsecurityjwt.service;

import com.muslimov.vlad.authsecurityjwt.dto.UserCreateDto;
import com.muslimov.vlad.authsecurityjwt.dto.UserDto;
import com.muslimov.vlad.authsecurityjwt.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {


    User findByName(String name);

    UserDto findById(Long id);

    UserDto save(UserCreateDto userCreateDto);

    void delete(Long id);

}
