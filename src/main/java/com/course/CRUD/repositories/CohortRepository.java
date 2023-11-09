package com.course.CRUD.repositories;

import com.course.CRUD.models.Cohort;
import com.course.CRUD.models.subModels.Mentor;
import com.course.CRUD.models.subModels.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CohortRepository extends JpaRepository<Cohort, Long> {
    List<Cohort> findAllByStudent(Student student);
    List<Cohort> findAllByMentor(Mentor mentor);
}
