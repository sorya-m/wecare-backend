package com.company.serviceImpl;

import com.company.dto.CoachDTO;
import com.company.dto.LoginDTO;
import com.company.entity.Coach;
import com.company.exception.CoachNotFoundException;
import com.company.repository.CoachRepository;
import com.company.service.CoachService;
import com.company.utility.CompanyConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:ValidationMessages.properties")
public class CoachServiceImpl implements CoachService {

    @Autowired
    private Environment env;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CoachRepository coachRepository;


    @Override
    public String createCoach(CoachDTO coachdto) {
        Coach coach = modelMapper.map(coachdto, Coach.class);
        Coach savedCoach = coachRepository.save(coach);

        return savedCoach.getCoachId();

    }

    @Override
    public boolean loginCoach(LoginDTO logindto) throws CoachNotFoundException {

        Coach coach = coachRepository.findById(logindto.getId()).orElseThrow(() -> new CoachNotFoundException(
                env.getProperty(CompanyConstants.COACH_NOT_FOUND.getValue())
        ));

        return coach.getPassword().equals(logindto.getPassword()) ? true : false;
    }

    @Override
    public CoachDTO getCoachProfile(String coachId) throws CoachNotFoundException {
        Coach coach = coachRepository.findById(coachId).orElseThrow(() -> new CoachNotFoundException(
                env.getProperty(CompanyConstants.COACH_NOT_FOUND.getValue())
        ));

        return modelMapper.map(coach, CoachDTO.class);
    }

    @Override
    public List<CoachDTO> showAllCoaches() {
        return coachRepository.findAll().stream()
                .map(coach -> modelMapper.map(coach, CoachDTO.class))
                .collect(Collectors.toList());
    }
}
