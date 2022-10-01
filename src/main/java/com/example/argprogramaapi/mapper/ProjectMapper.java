package com.example.argprogramaapi.mapper;

import com.example.argprogramaapi.model.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public Project updateProject(Long id, Project projectUpdates){
        return Project.builder()
                .id(id)
                .name(projectUpdates.getName())
                .description(projectUpdates.getDescription())
                .urlDemo(projectUpdates.getUrlDemo())
                .urlRepo(projectUpdates.getUrlRepo())
                .image(projectUpdates.getImage())
                .startDate(projectUpdates.getStartDate())
                .build();
    }
}
