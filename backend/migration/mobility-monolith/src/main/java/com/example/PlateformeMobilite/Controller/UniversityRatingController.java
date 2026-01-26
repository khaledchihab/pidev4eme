package com.example.PlateformeMobilite.Controller;

import com.example.PlateformeMobilite.Entity.University;
import com.example.PlateformeMobilite.Entity.UniversityRating;
import com.example.PlateformeMobilite.Repository.UniversityRepository;
import com.example.PlateformeMobilite.Services.UniversityRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

@RequestMapping("/universities")
@RequiredArgsConstructor
public class UniversityRatingController {
    private final UniversityRatingService ratingService;
    private final UniversityRepository universityRepository;

    @PostMapping("/{universityId}/rate")
    public UniversityRating rateUniversity(
            @PathVariable Long universityId,
            @RequestParam String userId,
            @RequestParam int rating) {
        return ratingService.rateUniversity(universityId, userId, rating);
    }

    @GetMapping("/{universityId}/rating")
    public double getAverageRating(@PathVariable Long universityId) {
        return ratingService.getAverageRating(universityId);
    }
    @GetMapping("/universities-with-avg-rating")
    public List<University> getUniversitiesWithAvgRating() {
        List<University> universities = universityRepository.findAll();
        for (University u : universities) {
            u.setAvgRating(
                    u.getRatings().isEmpty() ? null :
                            u.getRatings().stream().mapToInt(UniversityRating::getRating).average().orElse(0)
            );
        }
        return universities;
    }
}