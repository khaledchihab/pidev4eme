package com.example.PlateformeMobilite.Services;

import com.example.PlateformeMobilite.DTO.FormDTO;
import com.example.PlateformeMobilite.DTO.UniversityDTO;
import com.example.PlateformeMobilite.Entity.University;
import com.example.PlateformeMobilite.Interfaces.IUniversityService;
import com.example.PlateformeMobilite.Repository.UniversityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UniversityService implements IUniversityService {

    private final UniversityRepository universityRepository;
    @Override
    public List<University> retrieveAllUniversities() {
        return universityRepository.findAllWithForms();
    }
    @Override
    public List<UniversityDTO> getAllUniversities() {
        List<University> universities = universityRepository.findAllWithForms();

        return universities.stream().map(u -> new UniversityDTO(
                u.getUniversityId(),
                u.getName(),
                u.getLocation(),
                u.getForms().stream()
                        .map(f -> new FormDTO(
                                u.getUniversityId(),        // parent universityId
                                f.getFormName(),
                                f.getDescription(),
                                f.getPlacesDisp(),
                                f.getDatelimite()
                        ))
                        .collect(Collectors.toList()) // ✅ instead of .toList()
        )).collect(Collectors.toList()); // ✅ instead of .toList()
    }


    @Override
    public University retrieveUniversityById(Long UniversityId) {
        //return universityRepository.findById(UniversityId).get();


    Optional<University> optionalUniversity = universityRepository.findById(UniversityId);
    return optionalUniversity.orElse(null); }// Return null if not found

    @Override
    public University addUniversity(University u) {
        return universityRepository.save(u);
    }

    @Override
    public University updateUniversity(Long id,University u) {
        return universityRepository.findById(id)
                .map(p->{
                    p.setLocation(u.getLocation());
                    p.setName((u.getName()));
                    return universityRepository.save(u);
                }).orElseThrow(()-> new RuntimeException("university not found!"));
    }

    @Override
    public void removeUniversity(Long UniversityId) {
        universityRepository.deleteById(UniversityId);

    }
}
