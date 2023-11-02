package com.course.CRUD.repositories;

import com.course.CRUD.models.Modules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModulesRepository extends JpaRepository<Modules, Long> {
}
