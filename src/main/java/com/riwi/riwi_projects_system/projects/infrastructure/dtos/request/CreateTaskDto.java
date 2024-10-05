package com.riwi.riwi_projects_system.projects.infrastructure.dtos.request;

import com.riwi.riwi_projects_system.common.infrastructure.validators.enums.ValidEnum;
import com.riwi.riwi_projects_system.projects.domain.TaskStates;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskDto {

  @Schema(example = "Task 1", description = "Title of the task")
  @NotBlank(message = "Task title cannot be blank")
  private String title;

  @Schema(example = "UNINITIALIZED", description = "State of the task", implementation = TaskStates.class)
  @ValidEnum(enumClass = TaskStates.class, message = "Task state must be either 'UNINITIALIZED', 'IN_PROGRESS', or 'COMPLETED'")
  @NotBlank(message = "Task state cannot be blank")
  private String state;

  private String description;
}
