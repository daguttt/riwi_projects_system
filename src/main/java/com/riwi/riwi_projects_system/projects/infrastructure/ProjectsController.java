package com.riwi.riwi_projects_system.projects.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.riwi_projects_system.projects.aplication.ProjectsService;
import com.riwi.riwi_projects_system.projects.domain.ProjectEntity;
import com.riwi.riwi_projects_system.projects.infrastructure.dtos.request.CreateProjectDto;
import com.riwi.riwi_projects_system.projects.infrastructure.dtos.response.ProjectCreatedResponseDto;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/projects")
public class ProjectsController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProjectsService projectsService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<ProjectCreatedResponseDto> createProject(
            @Valid @RequestBody CreateProjectDto createProjectDto) {
        this.logger.info("Creating project: {}", createProjectDto);
        // projectsService.createProject(createProjectDto);
        ProjectCreatedResponseDto projectCreatedResponseDto = ProjectCreatedResponseDto
                .builder().status(201).message("Project created successfully").build();
        return ResponseEntity.status(HttpStatus.CREATED).body(projectCreatedResponseDto);
    }

    @GetMapping("/{id}")
    // public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable
    // id) {
    public ResponseEntity<String> getProjectById(@PathVariable Long id) {
        // ProjectEntity projectEntity = projectsService.getProjectById(id);

        // projectsService.getProjectById(id).map(ResponseEntity::
        // ok).orElse(ResponseEntity.notFound().build());
        return ResponseEntity.ok("Project with id" + id + " was found");
    }

    @GetMapping
    public ResponseEntity<String> getAllProjects() {
        // projectsService.getAllProjects();
        return ResponseEntity.ok("Getting all the projects");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProjects(@PathVariable Long id,
            @RequestBody ProjectEntity projectsEntityDetails) {
        // projectsService.updateProjectsEntity(id,projectsEntityDetails);
        return ResponseEntity.ok("Updating the project with id: " + id);
    }

@DeleteMapping("/{id}")
public ResponseEntity<ProjectEntity> deleteProject(@PathVariable Long id) {
    projectsService.deleteProject(id);
    return ResponseEntity.noContent().build();
}

}