package com.example.PlateformeMobilite.Converter;

import com.example.PlateformeMobilite.DTO.FormDTO;
import com.example.PlateformeMobilite.Entity.*;
import com.example.PlateformeMobilite.Interfaces.IUniversityService;
import com.example.PlateformeMobilite.Repository.FormRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class FormConverter {
    private final IUniversityService us;
    private final FormRepository formRepository;
    public Form convertForm(FormDTO formDTO){

    University university = us.retrieveUniversityById(formDTO.getUniversityId());
        if (university == null) {
            // Handle the case where the university doesn't exist
            throw new IllegalArgumentException("University with ID " + formDTO.getUniversityId() + " not found");
        }
    Form form = new Form();
    form.setUniversity(university);
    form.setFormName(formDTO.getFormName());
    form.setDescription(formDTO.getDescription());
    form.setDatelimite(formDTO.getDatelimite());
    form.setPlacesDisp(formDTO.getPlacesDisp());
    System.out.println(form);
    return formRepository.save(form);




    // Save the FormData object
    //return fs.addFormData(formData);
}
}
