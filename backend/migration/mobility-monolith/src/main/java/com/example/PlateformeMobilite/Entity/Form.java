package com.example.PlateformeMobilite.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Form implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formId;

    @ManyToOne
    private University university;
    @OneToMany
    @OrderBy("moyenne DESC ")
    private List<Moyenne> moyennes;

    @OneToMany(fetch = FetchType.EAGER)

    private List <FormField> formFields;


    private String formName;
    private String description;
    private int placesDisp;

    private String equation;
    private Date datelimite;


}
