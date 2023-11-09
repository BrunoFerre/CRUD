package com.course.CRUD.dto.mentor;

import com.course.CRUD.models.enums.Role;
import com.course.CRUD.models.subModels.Mentor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetMentorDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String code;
    private Role role;
    private int age;
    public GetMentorDTO(Mentor mentor){
        this.id = mentor.getId();
        this.firstName = mentor.getFirstName();
        this.lastName = mentor.getLastName();
        this.email = mentor.getEmail();
        this.password = mentor.getPassword();
        this.code = mentor.getCode();
        this.age = mentor.getAge();
    }
}
