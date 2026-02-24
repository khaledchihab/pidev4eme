package com.example.PlateformeMobilite.Services;

import com.example.PlateformeMobilite.DTO.FormFieldDTO;
import com.example.PlateformeMobilite.Entity.Form;
import com.example.PlateformeMobilite.Entity.FormField;
import com.example.PlateformeMobilite.Interfaces.IFormFieldService;
import com.example.PlateformeMobilite.Repository.FormFieldRepository;
import com.example.PlateformeMobilite.Repository.FormRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class FormFieldService implements IFormFieldService {

    private final FormFieldRepository formFieldRepository;
    private final FormRepository formRepository;

    @Override
    public List<FormField> retrieveAllFormFields() {
        return formFieldRepository.findAll();
    }

    @Override
    public FormField retrieveFormFieldById(Long formFieldId) {
        return formFieldRepository.findById(formFieldId).get();
    }

    @Override
    public FormField addFormField(FormField f) {
        return formFieldRepository.save(f);
    }

    @Override
    public FormField updateFormField(Long id, FormField f) {
        return formFieldRepository.findById(id)
                .map(p -> {
                    p.setFieldType(f.getFieldType());
                    p.setFieldName((f.getFieldName()));
                    return formFieldRepository.save(f);
                }).orElseThrow(() -> new RuntimeException("Form not found!"));
    }

    @Override
    public void removeFormField(Long FormFieldId) {
        formFieldRepository.deleteById(FormFieldId);

    }

    public FormField createFormField(FormFieldDTO request) {
        Form form = formRepository.findById(request.getFormId())
                .orElseThrow(() -> new EntityNotFoundException("Form not found"));

        FormField formField = new FormField();
        formField.setForm(form);
        formField.setFieldName(request.getFieldName());
        formField.setFieldLabel(request.getFieldLabel());
        formField.setFieldType(request.getFieldType());
        formField.setConfiguration(request.getConfiguration());
        formField.setCoefficient(request.getCoefficient());

        return formFieldRepository.save(formField);
    }

    public List<FormField> getFieldsByForm(Long formId) {
        return formFieldRepository.findByForm_FormId(formId);
    }

    public void deleteFormField(Long fieldId) {
        formFieldRepository.deleteById(fieldId);
    }

    @Override
    public List<FormField> getFormFieldsByFormId(Long formId) {
        return formFieldRepository.findByForm_FormId(formId);
    }
}
