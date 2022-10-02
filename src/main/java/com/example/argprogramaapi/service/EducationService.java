package com.example.argprogramaapi.service;

import com.example.argprogramaapi.model.Education;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EducationService {
    Education save(Education education);

    Education findById(Long id);

    List<Education> getAll();

    Education updateEducation(Long id, Education education);

    void delete(Long id) throws IOException;

    Education uploadImage(MultipartFile archivo, Long id) throws IOException;

    void deleteImage(Long id) throws IOException;
}
