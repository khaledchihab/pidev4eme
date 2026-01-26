package com.example.PlateformeMobilite.Repository;

import com.example.PlateformeMobilite.Entity.FormField;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormFieldRepository extends JpaRepository<FormField,Long> {
    List<FormField> findByForm_FormId(Long formId);
}
