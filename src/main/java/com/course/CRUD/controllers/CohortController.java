package com.course.CRUD.controllers;

import com.course.CRUD.dto.cohort.CohortDTO;
import com.course.CRUD.dto.cohort.GetCohortDTO;
import com.course.CRUD.models.Cohort;
import com.course.CRUD.repositories.AdminRepository;
import com.course.CRUD.repositories.CohortRepository;
import com.course.CRUD.repositories.MentorRepository;
import com.course.CRUD.repositories.StudentRepository;
import com.course.CRUD.models.subModels.Admin;
import com.course.CRUD.models.subModels.Mentor;
import com.course.CRUD.models.subModels.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/cohort")
public class CohortController {
    @Autowired
    private CohortRepository cohortRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private MentorRepository mentorRepository;

    @PostMapping
    public ResponseEntity<Object> createCohort(@RequestBody CohortDTO cohortDTO, Authentication authentication) {
        Admin admin = adminRepository.findByEmail(authentication.getName());
        Cohort cohort = new Cohort(true, cohortDTO.getInitial_students(), cohortDTO.getStart_date(), cohortDTO.getNumber_cohort());
        return new ResponseEntity<>("Cohort created", HttpStatus.OK);
    }
    @GetMapping("/student")
    public ResponseEntity<Object> getCohortByStudent(Authentication authentication) {
        Student student = studentRepository.findByEmail(authentication.getName());
        List<GetCohortDTO> getCohorts = cohortRepository.findAllByStudent(student).stream().map(GetCohortDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(getCohorts, HttpStatus.OK);
    }
    @GetMapping("/mentor")
    public ResponseEntity<Object> getCohortByMentor(Authentication authentication) {
        Mentor mentor = mentorRepository.findByEmail(authentication.getName());
        List<GetCohortDTO> getCohorts = cohortRepository.findAllByMentor(mentor).stream().map(GetCohortDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(getCohorts, HttpStatus.OK);
    }

}
