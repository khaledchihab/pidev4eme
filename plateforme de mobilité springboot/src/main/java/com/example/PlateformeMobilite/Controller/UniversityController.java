package com.example.PlateformeMobilite.Controller;

import com.example.PlateformeMobilite.DTO.UniversityDTO;
import com.example.PlateformeMobilite.Entity.University;
import com.example.PlateformeMobilite.Interfaces.IUniversityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

public class UniversityController {

    private final IUniversityService ur;

    @PostMapping("/addUniversity")
    public University addUniversity(@RequestBody University u){
        return ur.addUniversity(u);

    }
    @GetMapping("/getUniversities")
    public List<University> getUniversities(){
        return ur.retrieveAllUniversities();
    }
    @GetMapping("/getUniversitiesWithForms")
    public List<UniversityDTO> getUniversitiesWithForms(){
        return ur.getAllUniversities();
    }
    @PutMapping("/updateUniversity/{id}")
    public University updateUniversity(@PathVariable Long id,@RequestBody University u){
        return ur.updateUniversity(id,u);
    }
    @DeleteMapping("/deleteUniversity/{id}")
    public void deleteUniversity (@PathVariable Long id){
        ur.removeUniversity(id);
    }
}
