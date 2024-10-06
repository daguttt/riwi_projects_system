package com.riwi.riwi_projects_system.projects.infrastructure.dtos.response;

import com.riwi.riwi_projects_system.projects.domain.TaskStates;

public record UpdatedTaskStateDto(Long id, TaskStates state) {

}
