package com.example.PlateformeMobilite.Repository;

import com.example.PlateformeMobilite.Entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FormRepository extends JpaRepository <Form,Long> {
    Form findByFormId(Long formId);
    Optional<Form> findByFormName(String formName);
    @Query("SELECT DISTINCT fd.userId, f.formId " +
            "FROM FormData fd " +
            "JOIN fd.field.form f")
    List<Object[]> findUsersAndForms();

}
