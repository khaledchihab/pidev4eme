package com.example.PlateformeMobilite.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UniversityDTO {
    private Long universityId;
    private String name;
    private String location;
    private List<FormDTO> forms;
}