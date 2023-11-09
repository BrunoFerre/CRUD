package com.course.CRUD.controllers.Mentors;

import com.course.CRUD.models.Modules;
import com.course.CRUD.repositories.MentorRepository;
import com.course.CRUD.repositories.ModulesRepository;
import com.course.CRUD.models.subModels.Mentor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mentors/web")
public class WebMentorController {
    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private ModulesRepository modulesRepository;

    @PostMapping
    public ResponseEntity<Object> assignModulesToMentor(@RequestParam long id_module, Authentication authentication) {
        Mentor mentor = mentorRepository.findByEmail(authentication.getName());
        if (id_module <= 0) {
            return new ResponseEntity<>("Modules cannot be blank", HttpStatus.BAD_REQUEST);
        }
        Modules module = modulesRepository.findById(id_module).orElseThrow(() -> new RuntimeException("Modules not found"));
        mentor.addModules(module);
        mentorRepository.save(mentor);
        modulesRepository.save(module);
        return new ResponseEntity<>("Modules added", HttpStatus.OK);
    }

}
