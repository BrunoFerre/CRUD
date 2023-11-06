package com.course.CRUD.dto.student;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class StudentDTO {
    @NotBlank(message = "Cannot be blank")
    private String firstName;
    @NotBlank(message = "Cannot be blank")
    private String lastName;
    @NotBlank(message = "Cannot be blank")
    private String email;
    private String password;
    private int age;

}
