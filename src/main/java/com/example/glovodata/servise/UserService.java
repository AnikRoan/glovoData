package com.example.glovodata.servise;

import com.example.glovodata.dto.UserDto;
import com.example.glovodata.model.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
}
