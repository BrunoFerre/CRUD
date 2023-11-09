package com.course.CRUD.repositories;

import com.course.CRUD.models.Course;
import com.course.CRUD.models.Modules;
import com.course.CRUD.models.subModels.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModulesRepository extends JpaRepository<Modules, Long> {
    List<Modules> findAllByMentor(Mentor mentor);
    List<Modules> findAllByCourse(Course course);

    Optional<Modules> findByIdAndMentor(long id, Mentor mentor);
}
