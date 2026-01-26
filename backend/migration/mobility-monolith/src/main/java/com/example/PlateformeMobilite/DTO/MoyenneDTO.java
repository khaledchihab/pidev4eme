package com.example.PlateformeMobilite.DTO;

import com.example.PlateformeMobilite.Entity.Form;
import com.example.PlateformeMobilite.Entity.FormData;
import com.example.PlateformeMobilite.Entity.FormField;
import com.example.PlateformeMobilite.Repository.FormDataRepository;
import com.example.PlateformeMobilite.Repository.FormFieldRepository;
import com.example.PlateformeMobilite.Repository.FormRepository;
import lombok.Data;

import java.util.List;

@Data
public class MoyenneDTO {
    private double moyenne;
    private String userId;
    private Long formId;

    public void updateMoyenne(Long formId, String userId, FormRepository formRepository,
                              FormFieldRepository formFieldRepository,
                              FormDataRepository formDataRepository) {
        double average = calculateAverageForUser(formId, userId, formRepository,
                formFieldRepository, formDataRepository);
        this.moyenne = average;
    }
    private double calculateAverageForUser(Long formId, String userId, FormRepository formRepository,
                                           FormFieldRepository formFieldRepository,
                                           FormDataRepository formDataRepository) {
        Form form = formRepository.findById(formId).orElse(null);



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
        }}
}
