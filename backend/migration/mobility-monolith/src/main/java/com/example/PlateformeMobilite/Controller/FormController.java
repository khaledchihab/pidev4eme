package com.example.PlateformeMobilite.Controller;

import com.example.PlateformeMobilite.Converter.FormConverter;
import com.example.PlateformeMobilite.DTO.FormDTO;
import com.example.PlateformeMobilite.DTO.FormDataDTO;
import com.example.PlateformeMobilite.Entity.Form;
import com.example.PlateformeMobilite.Entity.FormData;
import com.example.PlateformeMobilite.Interfaces.IFormService;
import com.example.PlateformeMobilite.Repository.FormRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@AllArgsConstructor
public class FormController {
    private final IFormService fs;
    private final FormRepository formRepository;
    private final FormConverter formConverter;

//    @PostMapping("/addForm")
//    public Form addForm (@RequestBody Form f){
//        return fs.addForm(f);
//    }
    @GetMapping("/getForm")
    public List<Form> getForm(){
        return fs.retrieveAllForms();
    }
    @PutMapping("/updateForm/{id}")
    public Form updateForm(@PathVariable Long id,@RequestBody Form f){
        return fs.updateForm(id,f);
    }
    @DeleteMapping("/deleteForm/{id}")
    public void deleteForm(@PathVariable Long id){
         fs.removeForm(id);
    }
    @GetMapping("/forms/{formId}")
    public Form retrieveFormById(@PathVariable Long formId) {
        Optional<Form> form = formRepository.findById(formId);
        if (form.isPresent()) {
            return form.get();
        } else {
            // Handle the case where the form with the given ID is not found
            throw new FormNotFoundException("Form with ID " + formId + " not found");
        }
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class FormNotFoundException extends RuntimeException {
        public FormNotFoundException(String message) {
            super(message);
        }
    }

    @ExceptionHandler(FormNotFoundException.class)
    public ResponseEntity<String> handleFormNotFoundException(FormNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @GetMapping("/{formId}/average/{userId}")
    public double calculateAverageForUser(@PathVariable Long formId, @PathVariable String userId) {
        return fs.calculateAverageForUser(formId, userId);
    }
    @GetMapping("/getFormIdByName")
    public Long getFormIdByName(@RequestParam String formName) {
        return fs.getFormIdByName(formName);
    }
    @PostMapping("/addForm")
    public Form addFormNew(@RequestBody FormDTO formDTO) {
        return formConverter.convertForm(formDTO);
    }

    }
