package com.example.argprogramaapi.service.impl;

import com.example.argprogramaapi.mapper.ProjectMapper;
import com.example.argprogramaapi.model.Image;
import com.example.argprogramaapi.model.Profile;
import com.example.argprogramaapi.model.Project;
import com.example.argprogramaapi.repository.ProjectRepository;
import com.example.argprogramaapi.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ProfileServiceImpl profileService;

    @Override
    @Transactional
    public Project saveProject(Project project) {
        Profile myProfile = profileService.findFirstProfile();
        project.setProfile(myProfile);
        return projectRepository.save(project);
    }

    @Override
    @Transactional
    public Project updateProject(Project projectUpdates, Long id) {
        Project projectDb = findById(id);
        return projectRepository.save(projectMapper.updateProject(projectDb.getId(),projectUpdates));
    }

    @Override
    @Transactional(readOnly = true)
    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Project Not Found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteProject(Long id) {
        Project project = findById(id);
        if (project != null) projectRepository.deleteById(id);
    }

    @Override
    public Project uploadImage(MultipartFile archivo, Long id) throws IOException {
        Project projectDb = findById(id);
        imageService.biValidation(archivo);
        if (projectDb.getImage() != null) {
             deleteImage(id);
        }
        Image image = imageService.save(archivo);
        projectDb.setImage(image);
        return saveProject(projectDb);
    }

    @Override
    public void deleteImage(Long id) throws IOException {
        Project projectDb = findById(id);
        Long idDbImage = projectDb.getImage().getId();
        projectDb.setImage(null);
        saveProject(projectDb);
        imageService.delete(idDbImage);
    }
}
