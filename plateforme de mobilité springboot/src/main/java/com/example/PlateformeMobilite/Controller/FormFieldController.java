package com.example.PlateformeMobilite.Controller;

import com.example.PlateformeMobilite.Converter.FormFieldConverter;
import com.example.PlateformeMobilite.DTO.FormFieldDTO;
import com.example.PlateformeMobilite.Entity.Form;
import com.example.PlateformeMobilite.Entity.FormField;
import com.example.PlateformeMobilite.Interfaces.IFormFieldService;
import com.example.PlateformeMobilite.Services.FormFieldService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@AllArgsConstructor
public class FormFieldController {

    private final IFormFieldService fs;
    private final FormFieldConverter ffc;
    private final FormFieldService formFieldService;

    @PostMapping("/addFormField")
    public FormField addFormField (@RequestBody FormField f){
        return fs.addFormField(f);
    }
    @GetMapping("/getFormField")
    public List<FormField> getFormField(){
        return fs.retrieveAllFormFields();
    }
    @PutMapping("/updateFormField/{id}")
    public FormField updateFormField(@PathVariable Long id,@RequestBody FormField f){
        return fs.updateFormField(id,f);
    }

    @GetMapping("/byform/{formId}")
    public List<FormField> getFormFieldsByFormId(@PathVariable Long formId) {
        return fs.getFormFieldsByFormId(formId);
    }
    @PostMapping("/addFormFields")
    public List<FormField> addFormFields (@RequestBody List<FormFieldDTO> formFieldDTOS){
        return ffc.convertFormFields(formFieldDTOS);
    }

    @PostMapping
    public ResponseEntity<FormField> createFormField(@RequestBody FormFieldDTO request) {
        return ResponseEntity.ok(formFieldService.createFormField(request));
    }

    @GetMapping("/form/{formId}")
    public ResponseEntity<List<FormField>> getFieldsByForm(@PathVariable Long formId) {
        return ResponseEntity.ok(formFieldService.getFieldsByForm(formId));
    }

    @DeleteMapping("/{fieldId}")
    public ResponseEntity<Void> deleteFormField(@PathVariable Long fieldId) {
        formFieldService.deleteFormField(fieldId);
        return ResponseEntity.noContent().build();
    }

}
