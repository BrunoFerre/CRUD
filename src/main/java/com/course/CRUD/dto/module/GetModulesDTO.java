package com.course.CRUD.dto.module;

import com.course.CRUD.models.Modules;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class GetModulesDTO {
    private long id;
    private String name;
    private List<String> tecnologies = new ArrayList<>();
    private int hours;
    private boolean status;
    public GetModulesDTO(Modules modules){
        this.id = modules.getId();
        this.name = modules.getName();
        this.tecnologies = modules.getTecnologies();
        this.hours = modules.getHours();
        this.status = modules.isStatus();
    }
}
