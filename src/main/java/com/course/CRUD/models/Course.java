package com.course.CRUD.models;

import com.course.CRUD.models.enums.SectorType;
import com.course.CRUD.models.enums.Stack;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    private String name, description;
    private SectorType sectorType;
    private Stack stack;
    private boolean active, pay;

    //relation with cohort
    @OneToMany(mappedBy = "course")
    private Set<Cohort> cohort = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Modules modules;

    public Course(String name, String description, SectorType sectorType, Stack stack, boolean active, boolean pay) {
        this.name = name;
        this.description = description;
        this.sectorType = sectorType;
        this.stack = stack;
        this.active = active;
        this.pay = pay;
    }

    public void addCohort(Cohort cohort) {
        cohort.setCourse(this);
        this.cohort.add(cohort);
    }
}
