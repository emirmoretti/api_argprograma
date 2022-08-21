package com.example.argprogramaapi.service.impl;

import com.example.argprogramaapi.model.Education;
import com.example.argprogramaapi.model.Profile;
import com.example.argprogramaapi.repository.EducationRepository;
import com.example.argprogramaapi.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private ProfileServiceImpl profileService;

    @Override
    @Transactional
    public Education save(Education education, Long id) {
        Profile profile = profileService.findById(id);
        education.setProfile(profile);
        return educationRepository.save(education);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Education> getAll() {
        return educationRepository.findAll();
    }

    @Override
    @Transactional
    public Education edit(Education education) {
        return null;
    }

    @Override
    public void delete(Long id) {
        Education education = educationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Education not found"));
        educationRepository.deleteById(id);
        if(education != null){
            educationRepository.deleteById(id);
        }
    }

}
