package com.example.PlateformeMobilite.Services;

import com.example.PlateformeMobilite.DTO.ApplicationStatus;
import com.example.PlateformeMobilite.Entity.Form;
import com.example.PlateformeMobilite.Entity.FormApplication;
import com.example.PlateformeMobilite.Repository.FormApplicationRepository;
import javafx.application.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FormApplicationService {
    private final FormApplicationRepository applicationRepository;

    public FormApplication updateStatus(Long appId, ApplicationStatus newStatus) {
        FormApplication app = applicationRepository.findById(appId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        app.setStatus(newStatus);
        app.setUpdatedAt(LocalDateTime.now());
        return applicationRepository.save(app);
    }

    public List<FormApplication> getApplicationsByUser(String userId) {

        return applicationRepository.findByUserId(userId);
    }

    public List<FormApplication> getAllApplications() {
        return applicationRepository.findAll();
    }
}
