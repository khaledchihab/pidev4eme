package com.example.PlateformeMobilite.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;

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
