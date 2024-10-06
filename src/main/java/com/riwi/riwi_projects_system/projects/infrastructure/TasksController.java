package com.riwi.riwi_projects_system.projects.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.riwi_projects_system.common.infrastructure.dtos.response.ProblemDetailWithErrors;
import com.riwi.riwi_projects_system.projects.aplication.TasksService;
import com.riwi.riwi_projects_system.projects.infrastructure.dtos.request.CreateTaskDto;
import com.riwi.riwi_projects_system.projects.infrastructure.dtos.response.TaskRespondeDto.TaskResponseDtoData;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/tasks")
public class TasksController {

  @Autowired
  private TasksService tasksService;

  @Operation(summary = "View tasks", description = "Description: View all tasks")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Task created successfully", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = TaskResponseDtoData.class)) }),
      @ApiResponse(responseCode = "400", description = "The request body has invalid values", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailWithErrors.class)) }),
      @ApiResponse(responseCode = "401", description = "Unauthorized or Token Expired", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))) })

  @PostMapping
  public CreateTaskDto createTask(@RequestBody CreateTaskDto createTaskDto) {
    tasksService.createTask(createTaskDto);
    return createTaskDto;
  }
}