package com.course.CRUD.models.subModels;

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
public class Admin extends Person {
    @Column(name="admin_code")
    private String code;
    public Admin(String code,String firstName, String lastName, String email, String password, int age,boolean status) {
        super(firstName, lastName, email, password, age, status);
        this.code = code;
    }
}
