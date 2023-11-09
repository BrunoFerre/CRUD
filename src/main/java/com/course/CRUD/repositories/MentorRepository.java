package com.course.CRUD.repositories;

import com.course.CRUD.models.subModels.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    Mentor findByEmail(String email);
    boolean existsByCode(String mentor_code);
    Mentor findByCode(String mentorCode);
}
