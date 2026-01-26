package com.example.PlateformeMobilite.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FormField implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fieldId;

    @ManyToOne
    @OrderBy
    private Form form;

    private String fieldName;
    private String fieldLabel;
    private String fieldType;/* my own HTML type  selon this will take the input type with switch case*/
    private String configuration;

    private int coefficient;



}
