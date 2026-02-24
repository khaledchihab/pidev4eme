package com.example.PlateformeMobilite.Entity;

import com.example.PlateformeMobilite.Repository.FormDataRepository;
import com.example.PlateformeMobilite.Repository.FormFieldRepository;
import com.example.PlateformeMobilite.Repository.FormRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class Moyenne implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Form form;
    // @ManyToOne(fetch = FetchType.EAGER)
    private String userId;

    private double moyenne;

}
