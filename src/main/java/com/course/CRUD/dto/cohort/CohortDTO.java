package com.course.CRUD.dto.cohort;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CohortDTO {
    @NotBlank(message = "Name cannot be blank")
    private int initial_students;
    @NotBlank(message = "Start date cannot be blank")
    private LocalDate start_date;
    @NotBlank(message = "Number cohort cannot be blank")
    private int number_cohort;

}
