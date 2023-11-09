package com.course.CRUD.repositories;

import com.course.CRUD.models.Cohort;
import com.course.CRUD.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    Course findByCohort(Cohort cohort);
}
