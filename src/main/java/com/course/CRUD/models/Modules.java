package com.course.CRUD.models;

import com.course.CRUD.subModels.Mentor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Modules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> tecnologies = new ArrayList<>();

    private int hours;

    //relation with course
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;
    //relation with mentor

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    public Modules(String name, List<String> tecnologies, int totalHours) {
        this.name = name;
        this.tecnologies = tecnologies;
        this.hours = totalHours;
    }

}
