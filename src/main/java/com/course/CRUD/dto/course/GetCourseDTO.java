package com.course.CRUD.dto.course;

import com.course.CRUD.models.Course;
import com.course.CRUD.models.enums.SectorType;
import com.course.CRUD.models.enums.Stack;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GetCourseDTO {
    private int id;
    private String name, description;
    private SectorType sectorType;
    private Stack stack;
    private boolean active, pay;

    public GetCourseDTO(Course course){
        this.id = course.getId();
        this.name = course.getName();
        this.description = course.getDescription();
        this.sectorType = course.getSectorType();
        this.stack = course.getStack();
        this.active = course.isActive();
        this.pay = course.isPay();
    }
}
