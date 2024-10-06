package com.riwi.riwi_projects_system.projects.infrastructure.dtos.response;

import com.riwi.riwi_projects_system.common.infrastructure.dtos.response.ApiResponseDto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ProjectCreatedResponseDto extends ApiResponseDto {
    public record ProjectCreatedResponseDtoData(Long projectId) {
    }

    private ProjectCreatedResponseDtoData data;

    public ProjectCreatedResponseDto(Integer status, String message) {
        super(status, message);
    }

}