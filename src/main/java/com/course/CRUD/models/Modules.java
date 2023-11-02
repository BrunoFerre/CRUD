package com.course.CRUD.models;

import com.course.CRUD.subModels.Mentors;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Modules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> tecnologies = new HashSet<>();

    private int hours;

    //relation with course
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;
    //relation with mentors

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mentor_id")
    private Mentors mentors;

    public Modules(String name, Set<String> tecnologies, int totalHours) {
        this.name = name;
        this.tecnologies = tecnologies;
        this.hours = totalHours;
    }

}
