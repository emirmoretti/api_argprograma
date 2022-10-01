package com.example.argprogramaapi.service;

import com.example.argprogramaapi.model.Project;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProjectService {

    Project saveProject(Project project);

    Project updateProject(Project project, Long id);

    Project findById(Long id);

    List<Project> getAllProjects();

    void deleteProject(Long id);

    Project uploadImage(MultipartFile file, Long id) throws IOException;
}
