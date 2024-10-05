package com.riwi.riwi_projects_system.projects.infrastructure.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateProjectsDto {

  @Schema(example = "Project 1", description = "Name of the project")
  @NotBlank(message = "Name cannot be blank")
  @Min(value = 3, message = "Name must be at least 3 character")
  private String name;

}
