package com.course.CRUD.controllers;

import com.course.CRUD.dto.mentor.MentorDTO;
import com.course.CRUD.models.Modules;
import com.course.CRUD.repositories.AdminRepository;
import com.course.CRUD.repositories.MentorRepository;
import com.course.CRUD.repositories.ModulesRepository;
import com.course.CRUD.subModels.Admin;
import com.course.CRUD.subModels.Mentor;
import com.course.CRUD.utils.GenerateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/mentor")
public class MentorController {
    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ModulesRepository modulesRepository;

    @PostMapping
    public ResponseEntity<Object> createMentor(@RequestBody MentorDTO mentorDTO) {
        String mentor_code;
        int code;
        do {
            code = GenerateCode.generateNumber(1, 1000);
            mentor_code = "MNT" + code;
        } while (mentorRepository.existsByCode(mentor_code));
        Mentor mentor = new Mentor(
                mentor_code, mentorDTO.getFirstName(), mentorDTO.getLastName(), mentorDTO.getEmail(), passwordEncoder.encode(mentorDTO.getPassword()), mentorDTO.getAge(), true
        );
        mentorRepository.save(mentor);
        return new ResponseEntity<>("Welcome Mentor" + mentorDTO.getFirstName(), HttpStatus.CREATED);
    }

    @PatchMapping("/delete")
    public ResponseEntity<Object> deleteMentor(@RequestParam String mentor_code, Authentication authentication) {
        Admin admin = adminRepository.findByEmail(authentication.getName());
        if (admin == null) {
            return new ResponseEntity<>("Admin not found", HttpStatus.BAD_REQUEST);
        }
        if (mentor_code.isBlank()) {
            return new ResponseEntity<>("Code cannot be blank", HttpStatus.BAD_REQUEST);
        }
        Mentor mentor = mentorRepository.findByCode(mentor_code);
        if (mentor != null || mentor.isStatus()) {
            mentor.setStatus(false);
            mentorRepository.save(mentor);
            return new ResponseEntity<>("Mentor deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Mentor not found", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> assignModulesToMentor(@RequestParam long id_module, Authentication authentication) {
        Mentor mentor = mentorRepository.findByEmail(authentication.getName());
        if (id_module <= 0) {
            return new ResponseEntity<>("Modules cannot be blank", HttpStatus.BAD_REQUEST);
        }
        Modules module = modulesRepository.findById(id_module).orElseThrow(()-> new RuntimeException("Modules not found"));
        mentor.addModules(module);
        mentorRepository.save(mentor);
        return new ResponseEntity<>("Modules added", HttpStatus.OK);
    }
}
