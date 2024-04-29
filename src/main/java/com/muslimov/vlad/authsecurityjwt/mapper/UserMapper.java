package com.muslimov.vlad.authsecurityjwt.mapper;

import com.muslimov.vlad.authsecurityjwt.dto.UserCreateDto;
import com.muslimov.vlad.authsecurityjwt.dto.UserDto;
import com.muslimov.vlad.authsecurityjwt.model.User;

public interface UserMapper {

    User toEntity(UserCreateDto userCreateDto);
    UserDto toDto(User user);
}
