package com.example.argprogramaapi.service;

import com.example.argprogramaapi.model.Experience;

import java.util.List;

public interface ExperienceService {
    Experience saveExperience(Experience experience);

    Experience getById(Long id);

    List<Experience> getAll();

    Experience updateExperience(Long id, Experience experience);

    void deleteExperience(Long id);

}
