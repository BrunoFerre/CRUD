package com.course.CRUD.repositories;

import com.course.CRUD.subModels.Mentors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends JpaRepository<Mentors, Long> {
}
