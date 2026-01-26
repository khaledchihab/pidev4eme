package com.example.PlateformeMobilite.Repository;

import com.example.PlateformeMobilite.Entity.UniversityRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface UniversityRatingRepository extends JpaRepository<UniversityRating, Long> {
    Optional<UniversityRating> findByUserIdAndUniversity_UniversityId(String userId, Long universityId);
    List<UniversityRating> findByUniversity_UniversityId(Long universityId);
}