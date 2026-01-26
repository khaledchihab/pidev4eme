package com.example.PlateformeMobilite.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Data
@Getter
@AllArgsConstructor
@Setter
public class FormDTO {
    private Long universityId;

    private String formName;
    private String description;
    private int placesDisp;
    private Date datelimite;
}
