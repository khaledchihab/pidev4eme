package com.example.PlateformeMobilite.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UniversityRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId; // user from another microservice
    private int rating; // value between 1 and 5

    @ManyToOne
    @JoinColumn(name = "university_id")
    @JsonIgnore
    private University university;
}
