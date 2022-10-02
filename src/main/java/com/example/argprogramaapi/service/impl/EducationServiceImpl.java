package com.example.argprogramaapi.service.impl;

import com.example.argprogramaapi.model.Education;
import com.example.argprogramaapi.model.Image;
import com.example.argprogramaapi.model.Profile;
import com.example.argprogramaapi.model.Project;
import com.example.argprogramaapi.repository.EducationRepository;
import com.example.argprogramaapi.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private ProfileServiceImpl profileService;

    @Autowired
    private ImageService imageService;

    @Override
    @Transactional
    public Education save(Education education) {
        Profile profile = profileService.findFirstProfile();
        education.setProfile(profile);
        return educationRepository.save(education);
    }

    @Override
    public Education findById(Long id) {
        return educationRepository.findById(id).orElseThrow(() -> new RuntimeException("Education not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Education> getAll() {
        return educationRepository.findAll();
    }

    @Override
    @Transactional
    public Education updateEducation(Long id, Education education) {
        Education educationDB = findById(id);
        if(educationDB != null) {
            educationDB.setName(education.getName());
            educationDB.setStartDate(education.getStartDate());
            educationDB.setImage(education.getImage());
            educationDB.setGraduationDate(education.getGraduationDate());
        }
        return educationRepository.save(educationDB);
    }

    @Override
    public void delete(Long id) throws IOException {
        Education education = educationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Education not found"));
        if(education.getImage() != null){
            imageService.delete(education.getImage().getId());
        }
        educationRepository.deleteById(id);
    }

    @Override
    public Education uploadImage(MultipartFile archivo, Long id) throws IOException {
        Education educationDb = findById(id);
        imageService.biValidation(archivo);
        if (educationDb.getImage() != null) {
            deleteImage(id);
        }
        Image image = imageService.save(archivo);
        educationDb.setImage(image);
        return save(educationDb);
    }

    @Override
    public void deleteImage(Long id) throws IOException {
        Education educationDb = findById(id);
        Long idDbImage = educationDb.getImage().getId();
        educationDb.setImage(null);
        save(educationDb);
        imageService.delete(idDbImage);
    }

}
