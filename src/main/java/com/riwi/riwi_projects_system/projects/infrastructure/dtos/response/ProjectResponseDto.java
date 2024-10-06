package com.riwi.riwi_projects_system.projects.infrastructure.dtos.response;

import com.riwi.riwi_projects_system.common.infrastructure.dtos.response.ApiResponseDto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ProjectResponseDto extends ApiResponseDto {
    public record ProjectResponseDtoData(Long id, String name) {
    }

    private ProjectResponseDtoData data;

    public ProjectResponseDto(Integer status, String message) {
        super(status, message);
    }
}
