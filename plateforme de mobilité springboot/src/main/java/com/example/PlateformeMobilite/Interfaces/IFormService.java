package com.example.PlateformeMobilite.Interfaces;

import com.example.PlateformeMobilite.Entity.Form;

import java.util.List;

public interface IFormService {
    public List<Form> retrieveAllForms();
    public Form retrieveFormById(Long formId);
    public Form addForm(Form f);
    public Form updateForm(Long id,Form f);
    public void removeForm(Long formId);
    public double calculateAverageForUser(Long formId, String userId) ;

    Long getFormIdByName(String formName);
}
