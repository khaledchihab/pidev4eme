package com.example.PlateformeMobilite.Services;

import com.example.PlateformeMobilite.Entity.Form;
import com.example.PlateformeMobilite.Entity.FormData;
import com.example.PlateformeMobilite.Entity.FormField;
import com.example.PlateformeMobilite.Interfaces.IFormService;
import com.example.PlateformeMobilite.Repository.FormDataRepository;
import com.example.PlateformeMobilite.Repository.FormFieldRepository;
import com.example.PlateformeMobilite.Repository.FormRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class FormService implements IFormService {
    private final FormRepository formRepository;
    private final FormDataRepository formDataRepository;
    private final FormFieldRepository formFieldRepository;
    @Override
    public List<Form> retrieveAllForms() {
        return formRepository.findAll();
    }

    @Override
    public Form retrieveFormById(Long formId) {
        Optional<Form> optionalForm = formRepository.findById(formId);
        return optionalForm.orElse(null); // Return null if not found
    }

    @Override
    public Form addForm(Form f) {
        return formRepository.save(f);
    }

    @Override
    public Form updateForm(Long id,Form f) {
        return formRepository.findById(id)
                .map(p->{
                    p.setDescription(f.getDescription());
                    p.setFormName((f.getFormName()));
                    return formRepository.save(f);
                }).orElseThrow(()-> new RuntimeException("Form not found!"));
    }

    @Override
    public void removeForm(Long formId) {
        formRepository.deleteById(formId);

    }
//    public double calculateAverage(Long formId) {
//        Form form = formRepository.findById(formId).orElse(null);
//
////        if (form == null) {
////            throw new FormController.FormNotFoundException("Form not found with ID: " + formId);
////        }
//
//        double sumOfProducts = 0.0;
//        double sumOfCoefficients = 0.0;
//
//        for (FormField field : form.getFormFields()) {
//            if (field.getCoefficient() != null) {
//                FormData formData = formDataRepository.findByFieldAndForm(field, form);
//
//                if (formData != null) {
//                    int coefficient = field.getCoefficient();
//                    int fieldValue = Integer.parseInt(formData.getValue());
//
//                    double product = coefficient * fieldValue;
//                    sumOfProducts += product;
//                    sumOfCoefficients += coefficient;
//                }
//            }
//        }
//
//        if (sumOfCoefficients != 0) {
//            return sumOfProducts / sumOfCoefficients;
//        } else {
//            return 0.0;
//        }
//    }
    public double calculateAverageForUser(Long formId, String userId) {
        Form form = formRepository.findById(formId).orElse(null);

//        if (form == null) {
//            throw new FormNotFoundException("Form not found with ID: " + formId);
//        }

        double sumOfProducts = 0.0;
        double sumOfCoefficients = 0.0;
        List<FormField> formFields = formFieldRepository.findByForm_FormId(formId);


        for (FormField field : formFields) {
            if ( field.getCoefficient() != 0) {

                // Retrieve the FormData for the specific user and field
                Long fieldId= field.getFieldId();
                FormData formData2 = formDataRepository.findByUserIdAndField_FieldId(userId,fieldId );


                if (formData2 != null) {
                    Integer coefficient = field.getCoefficient();
                    Integer fieldValue = Integer.parseInt(formData2.getValue());
                    double product = coefficient * fieldValue;
                    sumOfProducts += product;
                    sumOfCoefficients += coefficient;
                }
            }
        }

        if (sumOfCoefficients != 0) {
            return sumOfProducts / sumOfCoefficients;
        } else {
            return 0.0;
        }
    }
    public Long getFormIdByName(String formName) {
        Optional<Form> formOptional = formRepository.findByFormName(formName);
        return formOptional.map(Form::getFormId).orElse(null);
    }

}
