package com.course.CRUD.models;

import com.course.CRUD.subModels.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate start_date;
    private int number_cohort;

    //relation with student
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    //relation with course
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;
    public Cohort( boolean active, int initial_students, LocalDate start_date,int number_cohort) {
        this.active = active;
        this.initial_students = initial_students;
        this.start_date = LocalDate.now();
        this.number_cohort = number_cohort;
    }
}
