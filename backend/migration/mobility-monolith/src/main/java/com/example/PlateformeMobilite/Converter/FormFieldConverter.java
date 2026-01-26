package com.example.PlateformeMobilite.Converter;

import com.example.PlateformeMobilite.DTO.FormDataDTO;
import com.example.PlateformeMobilite.DTO.FormFieldDTO;
import com.example.PlateformeMobilite.Entity.Form;
import com.example.PlateformeMobilite.Entity.FormData;
import com.example.PlateformeMobilite.Entity.FormField;
import com.example.PlateformeMobilite.Interfaces.IFormService;
import com.example.PlateformeMobilite.Repository.FormFieldRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FormFieldConverter {
    private final IFormService iFormService;
    private final FormFieldRepository ffr;

    public List<FormField> convertFormFields(List<FormFieldDTO> formFieldDTOList) {
        List<FormField> formFieldList = new ArrayList<>();
        Form loopForm = null; // Initialize to null
        for (FormFieldDTO formFieldDTO: formFieldDTOList){
            Long formId = formFieldDTO.getFormId();
            String fieldLabel = formFieldDTO.getFieldLabel();
            String fieldType = formFieldDTO.getFieldType();
            int coefficient = formFieldDTO.getCoefficient();
            String configuration = formFieldDTO.getConfiguration();
            String fieldName = formFieldDTO.getFieldName();

            Form form = iFormService.retrieveFormById(formId);
            // Create a new FormField object and set the values
            FormField formField = new FormField();
            formField.setForm(form);
            formField.setFieldName(fieldName);
            formField.setFieldType(fieldType);
            formField.setFieldLabel(fieldLabel);
            formField.setCoefficient(coefficient);
            formField.setConfiguration(configuration);
            // Save the FormField object and add it to the list
            formFieldList.add(ffr.save(formField));


        }
        return formFieldList;

    }
}
