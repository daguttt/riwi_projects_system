package com.riwi.riwi_projects_system.projects.infrastructure.dtos.request;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class CreateProjectDto {
    @Schema(example = "Project 1", description = "Name of the project")
    @Length(min = 3, message = "Name must be at least 3 character")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Schema(exampleClasses = { CreateTaskDto.class },
            description = "Tasks of the project")
    @NotNull
    @Size(min = 1, message = "At least one task is required")
    @Valid
    private CreateTaskDto[] tasks;
}
