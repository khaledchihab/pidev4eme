package com.example.PlateformeMobilite.Services;

import com.example.PlateformeMobilite.Entity.University;
import com.example.PlateformeMobilite.Entity.UniversityRating;
import com.example.PlateformeMobilite.Repository.UniversityRatingRepository;
import com.example.PlateformeMobilite.Repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityRatingService {
    private final UniversityRatingRepository ratingRepository;
    private final UniversityRepository universityRepository;

    public UniversityRating rateUniversity(Long universityId, String userId, int rating) {
        University university = universityRepository.findById(universityId)
                .orElseThrow(() -> new RuntimeException("University not found"));

        // check if user already rated
        UniversityRating userRating = ratingRepository.findByUserIdAndUniversity_UniversityId(userId, universityId)
                .orElse(new UniversityRating());

        userRating.setUserId(userId);
        userRating.setUniversity(university);
        userRating.setRating(rating);

        return ratingRepository.save(userRating);
    }

    public double getAverageRating(Long universityId) {
        List<UniversityRating> ratings = ratingRepository.findByUniversity_UniversityId(universityId);
        return ratings.stream().mapToInt(UniversityRating::getRating).average().orElse(0.0);
    }

}