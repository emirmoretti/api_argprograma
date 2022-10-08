package com.example.argprogramaapi.controller;

import com.example.argprogramaapi.model.Project;
import com.example.argprogramaapi.service.impl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
public class ProjectController {

    @Autowired
    private ProjectServiceImpl projectService;

    @GetMapping
    public ResponseEntity<?> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody Project projectRequest) {
        return ResponseEntity.ok(projectService.saveProject(projectRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable Long id, @RequestBody Project projectRequestUpdate) {
        return ResponseEntity.ok(projectService.updateProject(projectRequestUpdate, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        try {
            projectService.deleteProject(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile archivo, @RequestParam Long id) throws IOException {
        return ResponseEntity.ok(projectService.uploadImage(archivo, id));
    }
    @DeleteMapping("/image/{idProject}")
    public ResponseEntity<?> deleteImage(@PathVariable Long idProject) throws IOException {
        projectService.deleteImage(idProject);
        return ResponseEntity.ok("Imagen eliminada");
    }

}
