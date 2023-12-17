package com.company.serviceImpl;

import com.company.dto.LoginDTO;
import com.company.dto.UserDTO;
import com.company.entity.UserTable;
import com.company.exception.UserNotFoundException;
import com.company.repository.UserRepository;
import com.company.service.UserService;
import com.company.utility.CompanyConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:ValidationMessages.properties")
public class UserServiceImpl implements UserService {

    @Autowired
    private Environment env;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String createUser(UserDTO userdto) {

        UserTable userTable = modelMapper.map(userdto, UserTable.class);
        UserTable savedUserTable = userRepository.save(userTable);
        return savedUserTable.getUserId();
    }

    @Override
    public boolean loginUser(LoginDTO logindto) throws UserNotFoundException {

        UserTable userTable = userRepository.findById(logindto.getId()).orElseThrow(() -> new UserNotFoundException(
                env.getProperty(CompanyConstants.USER_NOT_FOUND.getValue())
        ));

        return userTable.getPassword().equals(logindto.getPassword()) ? true : false;
    }

    @Override
    public UserDTO userProfile(String userId) throws UserNotFoundException {

        UserTable userTable = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(
                env.getProperty(CompanyConstants.USER_NOT_FOUND.getValue())
        ));

        return modelMapper.map(userTable, UserDTO.class);
    }
}
