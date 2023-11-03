package com.course.CRUD.dto.module;

import java.util.HashSet;
import java.util.Set;

public class ModuleDTO {
    private int id;
    private String name;
    private Set<String> tecnologies = new HashSet<>();
    private int hours;

}
