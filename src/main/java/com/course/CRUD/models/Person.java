package com.course.CRUD.models;

import com.course.CRUD.models.enums.Role;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
@Data
@MappedSuperclass
public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private int age;
    private boolean status;
    public Person(String firstName, String lastName, String email, String password, int age, Role role, boolean status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.role = role;
        this.status = status;
    }


}
