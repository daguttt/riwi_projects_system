package com.riwi.riwi_projects_system.projects.aplication;

import org.springframework.stereotype.Service;

import com.riwi.riwi_projects_system.projects.domain.TasksEntity;
import com.riwi.riwi_projects_system.projects.domain.TasksRepository;
import com.riwi.riwi_projects_system.projects.infrastructure.dtos.request.CreateTaskDto;

@Service
public class TasksService {

  private final TasksRepository tasksRepository;

  public TasksService(TasksRepository tasksRepository) {
    this.tasksRepository = tasksRepository;
  }

  public TasksEntity createTask(CreateTaskDto createTaskDto) {
    TasksEntity tasksEntity = new TasksEntity();
    tasksEntity.setTitle(createTaskDto.getTitle());
    tasksEntity.getState();
    tasksEntity.setDescription(createTaskDto.getDescription());
    return tasksRepository.save(tasksEntity);
  }

}