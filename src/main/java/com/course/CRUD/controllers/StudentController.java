package com.course.CRUD.controllers;

import com.course.CRUD.dto.student.StudentDTO;
import com.course.CRUD.models.Cohort;
import com.course.CRUD.repositories.CohortRepository;
import com.course.CRUD.repositories.StudentRepository;
import com.course.CRUD.models.subModels.Student;
import com.course.CRUD.utils.GenerateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
@Controller
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CohortRepository cohortRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping
    public ResponseEntity<Object> createStudent(@RequestBody @Valid StudentDTO studentDTO) {
        String student_code;
        int code;
        do {
            code = GenerateCode.generateNumber(1, 1000);
            student_code = "STD-"+code+"-"+studentDTO.getFirstName();
        } while (studentRepository.existsByCode(student_code));
        Student student = new Student(
                student_code, studentDTO.getFirstName(), studentDTO.getLastName(), studentDTO.getEmail(), passwordEncoder.encode(studentDTO.getPassword()), studentDTO.getAge(), true
        );
        studentRepository.save(student);
        return new ResponseEntity<>("Welcome Student " + studentDTO.getFirstName(), HttpStatus.CREATED);
    }
    @PatchMapping
    public ResponseEntity<Object> assignCohortToStudent(@RequestParam @NotBlank(message = "Cohort cannot be blank") long id_cohort, Authentication authentication) {
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
