package com.example.PlateformeMobilite.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FormData implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dataId;

    // @ManyToOne
    // private Form form;

    private String userId;
    @ManyToOne
    private FormField field;

    private String value;
    @ManyToOne
    @JoinColumn(name = "application_id")
    @JsonBackReference
    private FormApplication application; // link to the whole application

    public FormData(String userId, FormField field, String value) {
        this.userId = userId;
        this.field = field;
        this.value = value;
    }

}
