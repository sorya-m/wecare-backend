package com.company.controller;

import com.company.dto.CoachDTO;
import com.company.dto.LoginDTO;
import com.company.exception.CoachNotFoundException;
import com.company.service.CoachService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coach")
@Validated
@CrossOrigin()
public class CoachController {

    @Autowired
    private CoachService coachService;

    @PostMapping()
    public ResponseEntity<String> createCoach(@Valid @RequestBody CoachDTO coachdto, Errors error) {

//        if (error.hasErrors()) {
//            String errors = error.getAllErrors().stream().map(data -> data.getDefaultMessage()).collect(Collectors.joining(","));
//            System.out.println(errors);
//        }
        return new ResponseEntity<>(coachService.createCoach(coachdto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> loginCoach(@RequestBody LoginDTO logindto) throws CoachNotFoundException {

        return new ResponseEntity<>(coachService.loginCoach(logindto), HttpStatus.OK);
    }


    @GetMapping("/{coachid}")
    public ResponseEntity<CoachDTO> getCoachProfile(@PathVariable("coachid") String coachid) throws CoachNotFoundException {
        return new ResponseEntity<>(coachService.getCoachProfile(coachid), HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<CoachDTO>> showAllCoaches() {
        return new ResponseEntity<>(coachService.showAllCoaches(), HttpStatus.OK);
    }

}
