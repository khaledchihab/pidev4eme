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
public class FormDashboardDTO {
    private Long formId;
    private String formName;
    private String description;
    private int placesDisp;
    private String status;
    private int applicantsCount;
    private List<UserApplicationDTO> applicants;
}
