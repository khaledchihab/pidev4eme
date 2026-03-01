package com.example.PlateformeMobilite.Controller;

import com.example.PlateformeMobilite.Entity.University;
import com.example.PlateformeMobilite.Entity.UniversityRating;
import com.example.PlateformeMobilite.Repository.UniversityRepository;
import com.example.PlateformeMobilite.Services.UniversityRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

@RequestMapping("/universities")
@RequiredArgsConstructor
public class UniversityRatingController {
    private final UniversityRatingService ratingService;
    private final UniversityRepository universityRepository;
    private final JdbcTemplate jdbcTemplate;

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
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM university");
        List<University> universities = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            University university = new University();

            Long universityId = extractUniversityId(row);
            university.setUniversityId(universityId);
            university.setName(extractString(row, "name", "Name", "nom"));
            university.setLocation(extractString(row, "location", "Location", "localisation"));
            university.setAvgRating(calculateAverageRating(universityId));

            universities.add(university);
        }

        return universities;
    }

    private Long extractUniversityId(Map<String, Object> row) {
        List<String> idCandidates = List.of("universityId", "university_id", "idUniversity", "id_university", "id");
        for (String key : idCandidates) {
            Object value = row.get(key);
            if (value instanceof Number number) {
                return number.longValue();
            }
        }
        return null;
    }

    private String extractString(Map<String, Object> row, String... keys) {
        for (String key : keys) {
            Object value = row.get(key);
            if (value != null) {
                return value.toString();
            }
        }
        return null;
    }

    private Double calculateAverageRating(Long universityId) {
        if (universityId == null) {
            return 0.0;
        }

        String ratingFkColumn = detectRatingForeignKeyColumn();
        if (ratingFkColumn == null) {
            return 0.0;
        }

        Double avg = jdbcTemplate.queryForObject(
                "SELECT COALESCE(AVG(rating), 0) FROM university_rating WHERE " + ratingFkColumn + " = ?",
                Double.class,
                universityId
        );
        return avg == null ? 0.0 : avg;
    }

    private String detectRatingForeignKeyColumn() {
        String sql = """
                SELECT column_name
                FROM information_schema.columns
                WHERE table_schema = DATABASE()
                  AND table_name = 'university_rating'
                  AND column_name IN ('university_id', 'universityId', 'idUniversity', 'id_university')
                LIMIT 1
                """;

        List<String> columns = jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("column_name"));
        return columns.isEmpty() ? null : columns.get(0);
    }
}