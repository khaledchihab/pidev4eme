package com.example.PlateformeMobilite.DTO;

import com.example.PlateformeMobilite.Entity.Form;
import lombok.Data;

@Data
public class FormFieldDTO {
    private Long formId;

    private String fieldName;
    private String fieldLabel;
    private String fieldType;/* my own type mech HTML  selon type hedha bech yekhou input Lform b switch case*/
    private String configuration;
    private int coefficient;
}
