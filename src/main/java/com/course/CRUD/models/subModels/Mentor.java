package com.course.CRUD.models.subModels;

import com.course.CRUD.models.Modules;
import com.course.CRUD.models.Person;
import com.course.CRUD.models.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Mentor extends Person {
    @Column(name="mentor_code")
    private String code;
    //relationship with modules
    @OneToMany(mappedBy = "mentor")
    private Set<Modules> modules = new HashSet<>();

    public Mentor(String code,String firstName, String lastName, String email, String password, int age, boolean status) {
        super(firstName, lastName, email, password, age, status);
        this.code = code;
    }

    public void addModules(Modules modules){
        modules.setMentor(this);
        this.modules.add(modules);
    }

}
