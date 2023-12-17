package com.company.controller;


import com.company.dto.LoginDTO;
import com.company.dto.UserDTO;
import com.company.exception.UserNotFoundException;
import com.company.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin()
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userdto) {
        return new ResponseEntity<>(userService.createUser(userdto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> loginUser(@RequestBody LoginDTO logindto) throws UserNotFoundException {
        System.out.println(logindto.toString());
        return new ResponseEntity<>(userService.loginUser(logindto), HttpStatus.OK);
    }


    @GetMapping("/{userid}")
    public ResponseEntity<UserDTO> getCoachProfile(@PathVariable("userid") String userid) throws UserNotFoundException {
        return new ResponseEntity<>(userService.userProfile(userid), HttpStatus.OK);
    }


}
