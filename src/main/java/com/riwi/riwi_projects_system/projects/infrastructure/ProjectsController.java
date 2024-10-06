package com.riwi.riwi_projects_system.projects.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.riwi_projects_system.common.infrastructure.dtos.response.ProblemDetailWithErrors;
import com.riwi.riwi_projects_system.projects.aplication.ProjectsService;
import com.riwi.riwi_projects_system.projects.domain.ProjectEntity;
import com.riwi.riwi_projects_system.projects.infrastructure.dtos.request.CreateProjectDto;
import com.riwi.riwi_projects_system.projects.infrastructure.dtos.response.ProjectCreatedResponseDto;
import com.riwi.riwi_projects_system.projects.infrastructure.dtos.response.ProjectCreatedResponseDto.ProjectCreatedResponseDtoData;
import com.riwi.riwi_projects_system.projects.infrastructure.dtos.response.ProjectResponseDto;
import com.riwi.riwi_projects_system.projects.infrastructure.dtos.response.ProjectResponseDto.ProjectResponseDtoData;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/projects")
public class ProjectsController {

    @Autowired
    private ProjectsService projectsService;

    @Operation(summary = "Create project",
            description = "Description: Creates a new project with associated tasks. At least one")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Project created successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ProjectCreatedResponseDto.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "The request body has invalid values",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ProblemDetailWithErrors.class)) }),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized or Token Expired",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))) })
    @PostMapping
    public ResponseEntity<ProjectCreatedResponseDto> createProject(
            @Valid @RequestBody CreateProjectDto createProjectDto) {
        Long projectId = this.projectsService.createProject(createProjectDto);
        ProjectCreatedResponseDto projectCreatedResponseDto = ProjectCreatedResponseDto
                .builder().status(HttpStatus.CREATED.value())
                .message("Project created successfully")
                .data(new ProjectCreatedResponseDtoData(projectId)).build();
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