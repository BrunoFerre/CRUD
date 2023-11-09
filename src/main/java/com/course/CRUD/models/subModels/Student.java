package com.course.CRUD.models.subModels;

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
public class Student extends Person {
    @Column(name="student_code")
    private String code;
    @OneToMany(mappedBy = "student")
    private Set<Cohort> cohorts = new HashSet<>();

    public Student(String code, String firstName, String lastName, String email, String password, int age, boolean status) {
        super(firstName, lastName, email, password, age, status);
        this.code = code;
    }

    public void addCohort(Cohort cohort) {
        cohort.setStudent(this);
        this.cohorts.add(cohort);
    }

}