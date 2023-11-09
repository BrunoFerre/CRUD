package com.course.CRUD.controllers;

import com.course.CRUD.dto.course.GetCourseDTO;
import com.course.CRUD.models.Cohort;
import com.course.CRUD.models.Course;
import com.course.CRUD.repositories.CohortRepository;
import com.course.CRUD.repositories.CourseRepository;
import com.course.CRUD.repositories.MentorRepository;
import com.course.CRUD.repositories.StudentRepository;
import com.course.CRUD.models.subModels.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CohortRepository cohortRepository;

    @GetMapping("/byCohort")
    public ResponseEntity<Object> getCourseByCohort(Authentication authentication, @RequestParam long id) {
        Student student = studentRepository.findByEmail(authentication.getName());
        Cohort cohort = cohortRepository.findById(id).orElseThrow(() -> new RuntimeException("Cohort not found"));
        Course course = courseRepository.findByCohort(cohort);
        GetCourseDTO getCourses = new GetCourseDTO(course);
        return new ResponseEntity<>(getCourses, HttpStatus.OK);
    }
}
