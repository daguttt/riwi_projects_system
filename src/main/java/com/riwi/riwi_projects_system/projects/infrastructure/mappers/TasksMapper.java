package com.riwi.riwi_projects_system.projects.infrastructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.riwi.riwi_projects_system.projects.domain.TasksEntity;
import com.riwi.riwi_projects_system.projects.infrastructure.dtos.response.TaskDto;
import com.riwi.riwi_projects_system.projects.infrastructure.dtos.response.UpdatedTaskStateDto;

@Mapper
public interface TasksMapper {
    TasksMapper INSTANCE = Mappers.getMapper(TasksMapper.class);

    TaskDto taskEntityToTaskDto(TasksEntity taskEntity);

    UpdatedTaskStateDto taskEntityToUpdatedTaskStateDto(TasksEntity taskEntity);
}