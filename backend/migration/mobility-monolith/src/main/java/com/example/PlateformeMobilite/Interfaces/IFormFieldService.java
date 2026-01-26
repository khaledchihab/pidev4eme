package com.example.PlateformeMobilite.Interfaces;

import com.example.PlateformeMobilite.Entity.FormField;

import java.util.List;

public interface IFormFieldService {
    public List<FormField> retrieveAllFormFields();
    public FormField retrieveFormFieldById(Long formFieldId);
    public FormField addFormField(FormField f);
    public FormField updateFormField(Long id,FormField f);
    public void removeFormField (Long FormFieldId);
    public List<FormField> getFormFieldsByFormId(Long formId);
}
