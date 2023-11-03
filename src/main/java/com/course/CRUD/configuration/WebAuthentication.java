package com.course.CRUD.configuration;

import com.course.CRUD.repositories.AdminRepository;
import com.course.CRUD.repositories.MentorRepository;
import com.course.CRUD.repositories.StudentRepository;
import com.course.CRUD.subModels.Admin;
import com.course.CRUD.subModels.Mentor;
import com.course.CRUD.subModels.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inputName -> {
            Mentor mentor = mentorRepository.findByEmail(inputName);
            if (mentor != null) {
                return new User(mentor.getEmail(), mentor.getPassword(), AuthorityUtils.createAuthorityList(mentor.getRole().name()));
            }
            Student student = studentRepository.findByEmail(inputName);
            if (student != null) {
                return new User(student.getEmail(), student.getPassword(), AuthorityUtils.createAuthorityList(student.getRole().name()));
            }
            Admin admin = adminRepository.findByEmail(inputName);
            if (admin != null) {
                return new User(admin.getEmail(), admin.getPassword(), AuthorityUtils.createAuthorityList(admin.getRole().name()));
            } else {
                throw new UsernameNotFoundException("User not found" + inputName);
            }
        });
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
