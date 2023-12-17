package com.company.service;

import com.company.dto.LoginDTO;
import com.company.dto.UserDTO;
import com.company.exception.UserNotFoundException;

public interface UserService {

    public String createUser(UserDTO userdto);

    public boolean loginUser(LoginDTO logindto) throws UserNotFoundException;

    public UserDTO userProfile(String userId) throws UserNotFoundException;
}
