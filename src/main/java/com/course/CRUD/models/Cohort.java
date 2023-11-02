package com.course.CRUD.models;

import com.course.CRUD.subModels.Mentors;
import com.course.CRUD.subModels.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Cohort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean active;
    private int initial_students;
    //relation with student
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;
    //relation with mentor
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mentor_id")
    private Mentors mentors;
    //relation with course
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;
    public Cohort(int id, boolean active, int initial_students) {
        this.id = id;
        this.active = active;
        this.initial_students = initial_students;
    }
}
