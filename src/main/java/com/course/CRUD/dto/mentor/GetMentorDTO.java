package com.course.CRUD.dto.mentor;

import com.course.CRUD.models.enums.Role;
import com.course.CRUD.subModels.Mentor;
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
    private String mentor_code;
    private Role role;
    private int age;
    public GetMentorDTO(Mentor mentor){
        this.id = mentor.getId();
        this.firstName = mentor.getFirstName();
        this.lastName = mentor.getLastName();
        this.email = mentor.getEmail();
        this.password = mentor.getPassword();
        this.mentor_code = mentor.getCode();
        this.role = mentor.getRole();
        this.age = mentor.getAge();
    }
}
