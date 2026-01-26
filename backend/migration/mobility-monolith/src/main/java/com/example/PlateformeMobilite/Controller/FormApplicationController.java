package com.example.PlateformeMobilite.Controller;

import com.example.PlateformeMobilite.DTO.ApplicationStatus;
import com.example.PlateformeMobilite.Entity.FormApplication;
import com.example.PlateformeMobilite.Services.FormApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("/api/applications")
public class FormApplicationController {

    private final FormApplicationService applicationService;

    @Autowired
    public FormApplicationController(FormApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // -------------------------------
    // Update application status (admin)
    // -------------------------------
    @PutMapping("/{appId}/status")
    public ResponseEntity<FormApplication> updateStatus(
            @PathVariable Long appId,
            @RequestParam("status") ApplicationStatus newStatus
    ) {
        FormApplication updatedApp = applicationService.updateStatus(appId, newStatus);
        return ResponseEntity.ok(updatedApp);
    }

    // -------------------------------
    // Get all applications for a specific user
    // -------------------------------
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FormApplication>> getApplicationsByUser(@PathVariable String userId) {
        List<FormApplication> userApps = applicationService.getApplicationsByUser(userId);
        return ResponseEntity.ok(userApps);
    }

    // -------------------------------
    // Get ALL applications (admin)
    // -------------------------------
    @GetMapping
    public ResponseEntity<List<FormApplication>> getAllApplications() {
        List<FormApplication> allApps = applicationService.getAllApplications();
        return ResponseEntity.ok(allApps);
    }
}

