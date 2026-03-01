package com.example.PlateformeMobilite.Entity;

import com.example.PlateformeMobilite.DTO.ApplicationStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "form_application")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormApplication implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long applicationId;

    @Column(name = "user_id")
    private String userId; // comes from your other microservice

    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ApplicationStatus status = ApplicationStatus.PENDING;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference

    private List<FormData> formDataList;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}