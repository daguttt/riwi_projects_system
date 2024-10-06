package com.riwi.riwi_projects_system.projects.infrastructure.dtos.response;

import com.riwi.riwi_projects_system.projects.domain.TaskStates;

public record TaskDto(Long id, String title, TaskStates state, String description) {
}