package com.course.CRUD.subModels;

import com.course.CRUD.models.Cohort;
import com.course.CRUD.models.Modules;
import com.course.CRUD.models.Person;
import com.course.CRUD.models.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Mentors extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String student_code;
    //relationship with cohorts
    @OneToMany(mappedBy = "mentors")
    private Set<Cohort> cohorts= new HashSet<>();
    //relationship with modules
    @OneToMany(mappedBy = "mentors")
    private Set<Modules> modules = new HashSet<>();
    public Mentors(String student_code, String firstName, String lastName, String email, String password, int age) {
        super(firstName, lastName, email, password, age,Role.ROLE_MENTOR);
        this.student_code = student_code;
    }
    public void addCohort(Cohort cohort){
        cohort.setMentors(this);
        this.cohorts.add(cohort);
    }
    public void addModules(Modules modules){
        modules.setMentors(this);
        this.modules.add(modules);
    }
}
