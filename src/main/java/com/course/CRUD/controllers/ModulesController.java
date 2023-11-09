package com.course.CRUD.controllers;

import com.course.CRUD.dto.module.GetModulesDTO;
import com.course.CRUD.dto.module.TechDTO;
import com.course.CRUD.models.Course;
import com.course.CRUD.models.Modules;
import com.course.CRUD.repositories.*;
import com.course.CRUD.models.subModels.Mentor;
import com.course.CRUD.models.subModels.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/modules")
public class ModulesController {
    @Autowired
    private ModulesRepository modulesRepository;
    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public ResponseEntity<List<GetModulesDTO>> getModules(Authentication authentication) {
        Mentor mentor = mentorRepository.findByEmail(authentication.getName());
        List<GetModulesDTO> getModulesDTOS = modulesRepository.findAllByMentor(mentor).stream().map(GetModulesDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(getModulesDTOS);
    }

    @GetMapping("/by-course")
    public ResponseEntity<List<GetModulesDTO>> getByCourse(Authentication authentication, @RequestParam long id) {
        Student student = studentRepository.findByEmail(authentication.getName());
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
        List<GetModulesDTO> getModulesDTOS = modulesRepository.findAllByCourse(course).stream().map(GetModulesDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(getModulesDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetModulesDTO> getModules(@PathVariable long id, Authentication authentication) {
        Mentor mentor = mentorRepository.findByEmail(authentication.getName());
        Modules modules = modulesRepository.findByIdAndMentor(id, mentor).orElseThrow(() -> new RuntimeException("Modules not found"));
        return new ResponseEntity<>(new GetModulesDTO(modules), HttpStatus.OK);
    }

    @PatchMapping("/update-name")
    public ResponseEntity<Object> updateName(@RequestParam long id, @RequestParam String name, Authentication authentication) {
        Mentor mentor = mentorRepository.findByEmail(authentication.getName());
        if (id <= 0) {
            return new ResponseEntity<>("Modules cannot be blank", HttpStatus.BAD_REQUEST);
        }
        Modules modules = modulesRepository.findByIdAndMentor(id, mentor).orElseThrow(() -> new RuntimeException("Modules not found"));
        modules.setName(name);
        modulesRepository.save(modules);
        return new ResponseEntity<>(new GetModulesDTO(modules), HttpStatus.OK);
    }

    @PatchMapping("/update-technologies")
    public ResponseEntity<Object> updateTechnologies(@RequestBody TechDTO technologies, Authentication authentication) {
        Mentor mentor = mentorRepository.findByEmail(authentication.getName());
        if (technologies.getId() <= 0) {
            return new ResponseEntity<>("Modules cannot be blank", HttpStatus.BAD_REQUEST);
        }
        long id = technologies.getId();
        Modules modules = modulesRepository.findByIdAndMentor(id, mentor).orElseThrow(() -> new RuntimeException("Modules not found"));
        modules.setTecnologies(technologies.getTechnologies());
        modulesRepository.save(modules);
        return new ResponseEntity<>(new GetModulesDTO(modules), HttpStatus.OK);
    }

    @PatchMapping("/update-hours")
    public ResponseEntity<Object> updateHours(@RequestParam long id, @RequestParam int hours, Authentication authentication) {
        Mentor mentor = mentorRepository.findByEmail(authentication.getName());
        if (id <= 0) {
            return new ResponseEntity<>("Modules cannot be blank", HttpStatus.BAD_REQUEST);
        }
        Modules modules = modulesRepository.findByIdAndMentor(id, mentor).orElseThrow(() -> new RuntimeException("Modules not found"));
        modules.setHours(hours);
        modulesRepository.save(modules);
        return new ResponseEntity<>(new GetModulesDTO(modules), HttpStatus.OK);
    }
}
