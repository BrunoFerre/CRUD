package com.course.CRUD.repositories;

import com.course.CRUD.models.Modules;
import com.course.CRUD.subModels.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModulesRepository extends JpaRepository<Modules, Long> {
    List<Modules> findAllByMentor(Mentor mentor);

    Optional<Modules> findByIdAndMentor(long id, Mentor mentor);
}
