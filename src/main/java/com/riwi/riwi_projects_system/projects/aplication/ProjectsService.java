package com.riwi.riwi_projects_system.projects.aplication;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.riwi.riwi_projects_system.projects.domain.ProjectsRepository;
import com.riwi.riwi_projects_system.projects.infrastructure.dtos.request.CreateProjectDto;
import com.riwi.riwi_projects_system.projects.domain.ProjectEntity;

@Service
public class ProjectsService {

    private final ProjectsRepository projectsRepository;

    public ProjectsService(ProjectsRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }

    public void createProject(CreateProjectDto createProjectsDto) {
        ProjectEntity projectsEntity = new ProjectEntity();
        projectsEntity.setName(createProjectsDto.getName());
        projectsRepository.save(projectsEntity);
    }

    public ProjectEntity updateProject(CreateProjectDto createProjectsDto, Long id) {
        ProjectEntity projectsEntity = projectsRepository.findById(id).orElseThrow();
        projectsEntity.setName(createProjectsDto.getName());
        return projectsRepository.save(projectsEntity);
    }

    public ProjectEntity getProjectById(Long id) {
        return this.projectsRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND,
                        "Project with id " + id + " not found"));
    }

    public List<ProjectEntity> getAllProjects() {
        return projectsRepository.findAll();
    }

    public void deleteProject(Long id) {
        ProjectEntity projectsEntity = projectsRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND,
                        "Project with id " + id + " not found"));
        projectsRepository.delete(projectsEntity);
    }

    public void updateProjectsEntity(Long id, CreateProjectDto createProjectDto) {
    }
}