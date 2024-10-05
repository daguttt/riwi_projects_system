package com.riwi.riwi_projects_system.projects.aplication;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.riwi.riwi_projects_system.projects.domain.ProjectsRepository;
import com.riwi.riwi_projects_system.projects.infrastructure.dtos.request.CreateProjectsDto;
import com.riwi.riwi_projects_system.projects.domain.ProjectsEntity;

@Service
public class ProjectsCreator {

  private final ProjectsRepository projectsRepository;

  public ProjectsCreator(ProjectsRepository projectsRepository) {
    this.projectsRepository = projectsRepository;
  }

  public ProjectsEntity createProject(CreateProjectsDto createProjectsDto) {
    ProjectsEntity projectsEntity = new ProjectsEntity();
    projectsEntity.setName(createProjectsDto.getName());
    return projectsRepository.save(projectsEntity);
  }

  public ProjectsEntity updateProject(CreateProjectsDto createProjectsDto, Long id) {
    ProjectsEntity projectsEntity = projectsRepository.findById(id).orElseThrow();
    projectsEntity.setName(createProjectsDto.getName());
    return projectsRepository.save(projectsEntity);
  }

  public Optional<CreateProjectsDto> getProjectById(Long id) {
    return projectsRepository.findById(id).map(entity -> new CreateProjectsDto(entity.getName()));
  }

  public List<CreateProjectsDto> getAllProjects() {
    return projectsRepository
        .findAll()
        .stream()
        .map(entity -> new CreateProjectsDto(entity.getName()))
        .toList();
  }

  public void deleteProject(CreateProjectsDto createProjectsDto) {
    ProjectsEntity projectsEntity = new ProjectsEntity();
    projectsEntity.setName(createProjectsDto.getName());
    projectsRepository.delete(projectsEntity);
  }

  // public ProjectsEntity updateProjectsEntity(Long id, CreateProjectsDto
  // createProjectsDto) {

  // }
}