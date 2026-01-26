package com.example.PlateformeMobilite.Controller;


import com.example.PlateformeMobilite.DTO.FormDataDTO;
import com.example.PlateformeMobilite.DTO.UserResponseDTO;
import com.example.PlateformeMobilite.Entity.*;
import com.example.PlateformeMobilite.Interfaces.*;
import com.example.PlateformeMobilite.Repository.FormApplicationRepository;
import com.example.PlateformeMobilite.Repository.FormDataRepository;
import com.example.PlateformeMobilite.Repository.MoyenneRepository;
import com.example.PlateformeMobilite.Services.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.PlateformeMobilite.DTO.ApplicationStatus;

import java.util.ArrayList;
import java.util.List;
import com.example.PlateformeMobilite.DTO.UserDTO;
@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@AllArgsConstructor
public class FormDataController {
    private final IFormDataService fs ;

    private final IFormFieldService ffs;
    private final MoyenneRepository moyenneRepository;
    private final MoyenneService moyenneService;
    private final FormDataRepository formDataRepository;
    private final UserClient userClient;
    private final FormApplicationRepository formApplicationRepository;

    @PostMapping("/addFormData")
    public FormData addFormData (@RequestBody FormData f){
        return fs.addFormData(f);
    }
    @GetMapping("/getFormData")
    public List<FormData> getFormData(){
        return fs.retrieveAllFormData();
    }
    @PutMapping("updateFormData/{id}")
    public FormData updateFormData (@PathVariable Long id, @RequestBody FormData f){
        return fs.updateFormData(id,f);
    }
    @DeleteMapping("/deleteFormData/{id}")
    public void deleteFormData(@PathVariable Long id){
        fs.removeFormData(id);
    }
//    @PostMapping("/submitFormData")
//    public ResponseEntity<String> submitFormData(@RequestBody FormData formData){
//
//    }
//@PostMapping("/submitFormData")
//public FormData addFormData(@RequestBody FormDataDTO formDataDTO) {
//    // Fetch user from User microservice
//    UserResponseDTO user = userClient.getUserById(formDataDTO.getUserId());
//
//    // Fetch FormField and derive the Form
//    FormField formField = ffs.retrieveFormFieldById(formDataDTO.getFormFieldId());
//    Form form = formField.getForm();
//
//    // Create and populate FormData
//    FormData formData = new FormData();
//    formData.setUserId(user.getId()); // use userId instead of whole User object
//    formData.setForm(form);
//    formData.setField(formField);
//    formData.setValue(formDataDTO.getValue());
//
//    // Save and return
//    return fs.addFormData(formData);
//}

    @PostMapping("/submitFormDatas")
    public FormApplication addFormData(@RequestBody List<FormDataDTO> formDataDTOList) {
        if (formDataDTOList.isEmpty()) {
            throw new IllegalArgumentException("No form data provided");
        }

        String userId = formDataDTOList.get(0).getUserId();
        Long firstFieldId = formDataDTOList.get(0).getFormFieldId();

        FormField firstField = ffs.retrieveFormFieldById(firstFieldId);
        if (firstField == null || firstField.getForm() == null) {
            throw new IllegalArgumentException("Invalid FormField: no form found for fieldId " + firstFieldId);
        }
        Form form = firstField.getForm();

        // Check if application exists
        FormApplication application =
                formApplicationRepository.findByUserIdAndForm(userId, form)
                        .orElseGet(() -> {
                            FormApplication newApp = new FormApplication();
                            newApp.setUserId(userId);
                            newApp.setForm(form);
                            newApp.setStatus(ApplicationStatus.PENDING);
                            newApp.setFormDataList(new ArrayList<>()); // ensure list initialized
                            return formApplicationRepository.save(newApp);
                        });

        if (application.getFormDataList() == null) {
            application.setFormDataList(new ArrayList<>());
        }

        for (FormDataDTO dto : formDataDTOList) {
            FormField field = ffs.retrieveFormFieldById(dto.getFormFieldId());
            if (field == null) {
                throw new IllegalArgumentException("Invalid FormField ID: " + dto.getFormFieldId());
            }

            FormData existing = formDataRepository.findByUserIdAndField_FieldId(userId, dto.getFormFieldId());

            if (existing != null) {
                existing.setValue(dto.getValue());
                formDataRepository.save(existing);
            } else {
                FormData fd = new FormData();
                fd.setUserId(userId);
                fd.setField(field);
                fd.setValue(dto.getValue());
                fd.setApplication(application);
                formDataRepository.save(fd);
                application.getFormDataList().add(fd);
            }
        }

        // moyenne calculation
        double calculatedMoyenne = moyenneService.calculateAverageForUser(form.getFormId(), userId);

        Moyenne moyenne = moyenneRepository.findByUserIdAndForm(userId, form)
                .orElseGet(() -> {
                    Moyenne m = new Moyenne();
                    m.setUserId(userId);
                    m.setForm(form);
                    return m;
                });

        moyenne.setMoyenne(calculatedMoyenne);
        moyenneRepository.save(moyenne);

        return formApplicationRepository.save(application);
    }


    @GetMapping("/getFormApplication")
    public List<FormApplication> getFormApplication(){
        return formApplicationRepository.findAll();
    }


}

