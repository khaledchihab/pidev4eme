package com.example.PlateformeMobilite.Repository;

import com.example.PlateformeMobilite.Entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UniversityRepository extends JpaRepository<University,Long> {
    @Query("SELECT u FROM University u LEFT JOIN FETCH u.forms")
    List<University> findAllWithForms();
}
