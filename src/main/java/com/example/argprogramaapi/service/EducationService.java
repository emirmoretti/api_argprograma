package com.example.argprogramaapi.service;

import com.example.argprogramaapi.model.Education;

import java.util.List;

public interface EducationService {
    Education save(Education education);

    Education findById(Long id);

    List<Education> getAll();

    Education updateEducation(Long id, Education education);

    void delete(Long id);
}
