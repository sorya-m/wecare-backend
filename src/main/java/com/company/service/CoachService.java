package com.company.service;

import com.company.dto.CoachDTO;
import com.company.dto.LoginDTO;
import com.company.exception.CoachNotFoundException;

import java.util.List;

public interface CoachService {

    public String createCoach(CoachDTO coachdto);

    public boolean loginCoach(LoginDTO logindto) throws CoachNotFoundException;

    public CoachDTO getCoachProfile(String coachId) throws CoachNotFoundException;

    public List<CoachDTO> showAllCoaches();

}
