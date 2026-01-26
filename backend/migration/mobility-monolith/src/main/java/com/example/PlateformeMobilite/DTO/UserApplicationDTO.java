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
public class UserApplicationDTO {
    private String userId;
    private List<FieldValueDTO> answers;
}