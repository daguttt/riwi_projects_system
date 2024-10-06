package com.riwi.riwi_projects_system.projects.aplication;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import com.riwi.riwi_projects_system.projects.domain.ProjectsRepository;
import com.riwi.riwi_projects_system.projects.domain.TaskStates;
import com.riwi.riwi_projects_system.projects.domain.TasksEntity;
import com.riwi.riwi_projects_system.projects.domain.TasksRepository;
import com.riwi.riwi_projects_system.projects.infrastructure.dtos.request.CreateProjectDto;
import com.riwi.riwi_projects_system.projects.infrastructure.dtos.request.CreateTaskDto;
import com.riwi.riwi_projects_system.projects.domain.ProjectEntity;

@Service
public class ProjectsService {
    private final ProjectsRepository projectsRepository;
    private final TasksRepository tasksRepository;

    public ProjectsService(ProjectsRepository projectsRepository,
            TasksRepository tasksRepository) {
        this.projectsRepository = projectsRepository;
        this.tasksRepository = tasksRepository;
    }

    @Transactional
    public Long createProject(CreateProjectDto createProjectDto) {
        // Create the project and save it in the database
        ProjectEntity projectsEntity = new ProjectEntity();
        projectsEntity.setName(createProjectDto.getName());
        ProjectEntity savedProjectEntity = this.projectsRepository.save(projectsEntity);

        // Create and save the tasks associating them with the project
        Set<TasksEntity> tasksSet = new HashSet<>();
        for (CreateTaskDto createTaskDto : createProjectDto.getTasks()) {
            TaskStates taskState = TaskStates.valueOf(createTaskDto.getState());

            var taskEntity = new TasksEntity();
            taskEntity.setTitle(createTaskDto.getTitle());
            taskEntity.setState(taskState);
            taskEntity.setDescription(createTaskDto.getDescription());
            taskEntity.setProject(savedProjectEntity);
            tasksSet.add(taskEntity);
        }
        this.tasksRepository.saveAll(tasksSet);

        return savedProjectEntity.getId();
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