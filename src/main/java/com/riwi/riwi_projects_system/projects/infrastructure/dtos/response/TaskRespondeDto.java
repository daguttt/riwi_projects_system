package com.riwi.riwi_projects_system.projects.infrastructure.dtos.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class TaskRespondeDto {
  public record TaskResponseDtoData(Long id, String name) {
  }

  private TaskResponseDtoData data;

  public TaskRespondeDto(Integer status, String message) {
    super();
  }
}
