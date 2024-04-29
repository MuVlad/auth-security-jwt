package com.muslimov.vlad.authsecurityjwt.mapper.impl;

import com.muslimov.vlad.authsecurityjwt.dto.UserCreateDto;
import com.muslimov.vlad.authsecurityjwt.dto.UserDto;
import com.muslimov.vlad.authsecurityjwt.mapper.UserMapper;
import com.muslimov.vlad.authsecurityjwt.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserCreateDto userCreateDto) {
        if ( userCreateDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( userCreateDto.getUsername() );
        user.email( userCreateDto.getEmail() );
        user.password( userCreateDto.getPassword() );

        return user.build();
    }

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setUsername( user.getUsername() );
        userDto.setEmail( user.getEmail() );
        userDto.setPassword( user.getPassword() );

        return userDto;
    }
}
