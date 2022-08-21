package com.example.argprogramaapi.service;

import com.example.argprogramaapi.model.Education;

import java.util.List;

public interface EducationService {
    Education save(Education education, Long idProfile);

    List<Education> getAll();

    Education edit(Education education);

    void delete(Long id);
}
