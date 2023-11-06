package com.course.CRUD.services.implement;

import com.course.CRUD.dto.student.StudentDTO;
import com.course.CRUD.repositories.StudentRepository;
import com.course.CRUD.services.StudentService;
import com.course.CRUD.subModels.Student;
import com.course.CRUD.utils.GenerateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentServImplements implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public void createStudent(StudentDTO studentDTO){
        String student_code;
        int code;
        do {
            code = GenerateCode.generateNumber(1, 1000);
            student_code = "STD-"+code+"-"+studentDTO.getFirstName().substring(1,3).toUpperCase();
        } while (studentRepository.existsByCode(student_code));
        Student student = new Student(
                student_code, studentDTO.getFirstName(), studentDTO.getLastName(), studentDTO.getEmail(), passwordEncoder.encode(studentDTO.getPassword()), studentDTO.getAge(), true
        );
        studentRepository.save(student);
    }
}
