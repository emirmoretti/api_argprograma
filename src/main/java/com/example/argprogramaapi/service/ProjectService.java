package com.example.argprogramaapi.service;

import com.example.argprogramaapi.model.Project;

import java.util.List;

public interface ProjectService {

    Project saveProject(Project project);

    Project updateProject(Project project);

    Project findById(Long id);

    List<Project> getAllProjects();

    void deleteProject();
}
