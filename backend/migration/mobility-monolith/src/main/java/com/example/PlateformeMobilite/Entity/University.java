package com.example.PlateformeMobilite.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class University implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "universityId")
    private Long universityId;

    private String name;
    private String location;
    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore // to avoid infinite recursion when serializing
    private List<Form> forms;
    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UniversityRating> ratings;

    @Transient
    private Double avgRating; // not stored, just for API response

}
