package com.course.CRUD.dto.cohort;

import com.course.CRUD.dto.course.GetCourseDTO;
import com.course.CRUD.dto.module.GetModulesDTO;
import com.course.CRUD.models.Cohort;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
public class GetCohortDTO {
    private int id;
    private boolean active;
    private int initial_students;
    private LocalDate start_date;
    public GetCohortDTO(Cohort cohort){
        this.id = cohort.getId();
        this.active = cohort.isActive();
        this.initial_students = cohort.getInitial_students();
        this.start_date = cohort.getStart_date();
    }
}
