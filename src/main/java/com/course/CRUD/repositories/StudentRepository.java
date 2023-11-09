package com.course.CRUD.repositories;

import com.course.CRUD.models.subModels.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findByEmail(String inputName);
    boolean existsByCode(String student_code);
}