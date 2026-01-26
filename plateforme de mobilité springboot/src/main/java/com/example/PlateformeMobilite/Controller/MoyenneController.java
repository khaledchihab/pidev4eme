package com.example.PlateformeMobilite.Controller;


import com.example.PlateformeMobilite.Entity.Moyenne;
import com.example.PlateformeMobilite.Services.MoyenneService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
public class MoyenneController {
    private final MoyenneService moyenneService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Moyenne>> getAllMoyennesWithUserAndForm() {
        List<Moyenne> moyennes = moyenneService.getAllMoyennesWithUserAndForm();
        return ResponseEntity.ok(moyennes);
    }
    @GetMapping("/byFormName")
    public ResponseEntity<List<Moyenne>> getMoyennesByFormName(@RequestParam String formName) {
        List<Moyenne> moyennes = moyenneService.getAllMoyennesByFormName(formName);
        return ResponseEntity.ok(moyennes);
    }
    @PostMapping("/create")
    public ResponseEntity<String> createMoyenneEntities() {
        try {
            moyenneService.createMoyenneEntitiesFromUsersAndForms();
            return ResponseEntity.ok("Moyenne entities created successfully");
        } catch (Exception e) {
            // Handle exceptions as needed
            return ResponseEntity.badRequest().body("Moyenne entity creation failed: " + e.getMessage());
        }
    }

}
