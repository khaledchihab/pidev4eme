package com.example.PlateformeMobilite.Repository;

import com.example.PlateformeMobilite.Entity.Form;
import com.example.PlateformeMobilite.Entity.Moyenne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MoyenneRepository extends JpaRepository<Moyenne, Long> {

    // Fetch all moyennes along with forms
    @Query("SELECT m FROM Moyenne m JOIN FETCH m.form f")
    List<Moyenne> findAllWithForm();

    // Check if moyenne exists for a given userId and formId
    Boolean existsByUserIdAndForm_FormId(String userId, Long formId);

    // Fetch all moyennes for a specific form name
    @Query("SELECT m FROM Moyenne m JOIN FETCH m.form f WHERE f.formName = :formName")
    List<Moyenne> findAllByFormName(@Param("formName") String formName);

    // Fetch all moyennes for a specific formId
    List<Moyenne> findAllByForm_FormId(Long formId);

    Optional<Moyenne> findByUserIdAndForm(String userId, Form form);
}
