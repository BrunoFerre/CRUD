package com.course.CRUD.subModels;

import com.course.CRUD.models.Cohort;
import com.course.CRUD.models.Person;
import com.course.CRUD.models.enums.Role;
import lombok.Builder;
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
public class Student extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String code;
    @OneToMany(mappedBy = "student")
    private Set<Cohort> cohorts= new HashSet<>();

    public Student(String code, String firstName, String lastName, String email, String password, int age,boolean status) {
        super(firstName, lastName, email, password, age,Role.ROLE_STUDENT,status);
        this.code = code;
    }
    public void addCohort(Cohort cohort){
        cohort.setStudent(this);
        this.cohorts.add(cohort);
    }

}