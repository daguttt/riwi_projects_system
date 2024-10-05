package com.riwi.riwi_projects_system.projects.infrastructure.dtos.response;

import com.riwi.riwi_projects_system.common.infrastructure.dtos.response.ApiResponseDto;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class ProjectResponseDto extends ApiResponseDto {
    public ProjectResponseDto(Integer status, String message) {
        super(status, message);
    }
}
