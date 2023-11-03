package com.course.CRUD.subModels;

import com.course.CRUD.models.Person;
import com.course.CRUD.models.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Admin extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String code;

    public Admin(String code, String firstName, String lastName, String email, String password, int age,boolean status) {
        super(firstName, lastName, email, password, age, Role.ROLE_ADMIN,status);
        this.code = code;
    }
}
