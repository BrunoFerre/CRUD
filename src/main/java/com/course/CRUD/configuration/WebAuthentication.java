package com.course.CRUD.configuration;

import com.course.CRUD.models.Person;
import com.course.CRUD.repositories.PersonRepository;
import com.course.CRUD.models.subModels.Admin;
import com.course.CRUD.models.subModels.Mentor;
import com.course.CRUD.models.subModels.Student;
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
    private PersonRepository personRepository;


    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inputName -> {
           Person person = personRepository.findByEmail(inputName);
           if (person !=null){
               if (person instanceof Admin){
                   return new User(person.getEmail(), person.getPassword(), AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
               }else if (person instanceof Mentor){
                   return new User(person.getEmail(), person.getPassword(), AuthorityUtils.createAuthorityList("ROLE_MENTOR"));
               }else if (person instanceof Student){
                   return new User(person.getEmail(), person.getPassword(), AuthorityUtils.createAuthorityList("ROLE_STUDENT"));
               }else{
                   throw new UsernameNotFoundException("User not found");
               }
           }else {
               throw new UsernameNotFoundException("User not found");
           }
        });
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
