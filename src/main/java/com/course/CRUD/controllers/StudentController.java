package com.course.CRUD.controllers;

import com.course.CRUD.models.Cohort;
import com.course.CRUD.repositories.CohortRepository;
import com.course.CRUD.repositories.StudentRepository;
import com.course.CRUD.subModels.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CohortRepository cohortRepository;

    @PatchMapping
    public ResponseEntity<Object> assignCohortToStudent(@RequestParam long id_cohort, Authentication authentication) {
        Student student = studentRepository.findByEmail(authentication.getName());
        if (id_cohort <= 0) {
            return new ResponseEntity<>("Cohort cannot be blank", HttpStatus.BAD_REQUEST);
        }
        Cohort cohort = cohortRepository.findById(id_cohort).orElseThrow(() -> new RuntimeException("Cohort not found"));
        if (cohort.getStudent().getId() == student.getId()) {
            return new ResponseEntity<>("Cohort already assigned", HttpStatus.BAD_REQUEST);
        }
        student.addCohort(cohort);
        cohort.setStudent(student);
        studentRepository.save(student);
        cohortRepository.save(cohort);
        return new ResponseEntity<>("Cohort added", HttpStatus.OK);
    }
}
