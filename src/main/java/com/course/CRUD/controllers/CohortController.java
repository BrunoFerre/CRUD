package com.course.CRUD.controllers;

import com.course.CRUD.dto.cohort.CohortDTO;
import com.course.CRUD.models.Cohort;
import com.course.CRUD.repositories.AdminRepository;
import com.course.CRUD.repositories.CohortRepository;
import com.course.CRUD.subModels.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cohort")
public class CohortController {
    @Autowired
    private CohortRepository cohortRepository;
    @Autowired
    private AdminRepository adminRepository;

    @PostMapping
    public ResponseEntity<Object> createCohort(@RequestBody CohortDTO cohortDTO, Authentication authentication) {
        Admin admin = adminRepository.findByEmail(authentication.getName());
        Cohort cohort = new Cohort(true, cohortDTO.getInitial_students(), cohortDTO.getStart_date(), cohortDTO.getNumber_cohort());
        
        return new ResponseEntity<>("Cohort created", HttpStatus.OK);
    }

}
