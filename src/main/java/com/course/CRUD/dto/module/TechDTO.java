package com.course.CRUD.dto.module;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TechDTO {
    private long id;
    private List<String> technologies = new ArrayList<>();

}
