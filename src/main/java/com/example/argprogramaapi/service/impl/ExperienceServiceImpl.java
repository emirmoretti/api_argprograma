package com.example.argprogramaapi.service.impl;

import com.example.argprogramaapi.model.Experience;
import com.example.argprogramaapi.model.Profile;
import com.example.argprogramaapi.repository.ExperienceRepository;
import com.example.argprogramaapi.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;
    @Autowired
    private  ProfileServiceImpl profileService;

    @Override
    @Transactional
    public Experience saveExperience(Experience experience) {
        Profile profile = profileService.findFirstProfile();
        experience.setProfile(profile);
        return experienceRepository.save(experience);
    }

    @Override
    @Transactional(readOnly = true)
    public Experience getById(Long id) {
        return experienceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Experience not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Experience> getAll() {
        return experienceRepository.findAll();
    }

    @Override
    @Transactional
    public Experience updateExperience(Long id, Experience experience) {
        Experience experienceDb = getById(id);
        experienceDb.setName(experience.getName());
        experienceDb.setGraduationDate(experience.getGraduationDate());
        experienceDb.setStartDate(experience.getStartDate());
        return saveExperience(experienceDb);
    }

    @Override
    @Transactional
    public void deleteExperience(Long id) {
        Experience experience = getById(id);
        experienceRepository.delete(experience);
    }
}
