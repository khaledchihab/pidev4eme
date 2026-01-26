package com.example.PlateformeMobilite.Repository;

import com.example.PlateformeMobilite.Entity.Form;
import com.example.PlateformeMobilite.Entity.FormApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FormApplicationRepository extends JpaRepository<FormApplication,Long> {
    Optional<FormApplication> findByUserIdAndForm(String userId, Form form);
    List<FormApplication> findByUserId(String userId);

}
