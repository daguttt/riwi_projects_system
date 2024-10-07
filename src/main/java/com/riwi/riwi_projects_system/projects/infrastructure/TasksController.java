package com.riwi.riwi_projects_system.projects.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.riwi_projects_system.common.infrastructure.dtos.response.ProblemDetailWithErrors;
import com.riwi.riwi_projects_system.projects.aplication.TasksService;
import com.riwi.riwi_projects_system.projects.domain.TaskStates;
import com.riwi.riwi_projects_system.projects.domain.TasksEntity;
import com.riwi.riwi_projects_system.projects.infrastructure.dtos.response.GetAllTasksResponseDto;
import com.riwi.riwi_projects_system.projects.infrastructure.dtos.response.TaskDto;
import com.riwi.riwi_projects_system.projects.infrastructure.dtos.response.UpdatedTaskStateDto;
import com.riwi.riwi_projects_system.projects.infrastructure.dtos.response.UpdatedTaskStateResponseDto;
import com.riwi.riwi_projects_system.projects.infrastructure.mappers.TasksMapper;

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
            @ApiResponse(responseCode = "200", description = "Tasks successfully fetched",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(
                                    implementation = GetAllTasksResponseDto.class)) }),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized or Token Expired",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))) })
    @GetMapping
    public ResponseEntity<GetAllTasksResponseDto> getAllTasks() {
        var taskEntityList = tasksService.getAllTasks();
        var taskDtoList = taskEntityList.stream()
                .map(TasksMapper.INSTANCE::taskEntityToTaskDto).toArray(TaskDto[]::new);
        var getAllTasksResponseDto = GetAllTasksResponseDto.builder()
                .status(HttpStatus.OK.value()).message("Tasks successfully fetched")
                .data(taskDtoList).build();
        return ResponseEntity.ok(getAllTasksResponseDto);
    }

    @Operation(summary = "Change task state",
            description = "Description: Change task state and notify users via email about the change")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Task state changed successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(
                                    implementation = UpdatedTaskStateResponseDto.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Invalid request parameter value",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ProblemDetailWithErrors.class))),
            @ApiResponse(responseCode = "404", description = "Task not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)) }), })
    @PatchMapping("/{id}")
    public UpdatedTaskStateResponseDto changeTaskState(@PathVariable Long id,
            @RequestParam(required = true) TaskStates newState) {
        TasksEntity updatedTask = this.tasksService.changeTaskState(id, newState);
        UpdatedTaskStateDto updatedTaskStateDto = TasksMapper.INSTANCE
                .taskEntityToUpdatedTaskStateDto(updatedTask);

        return UpdatedTaskStateResponseDto.builder().status(HttpStatus.OK.value())
                .message("Task state changed successfully").data(updatedTaskStateDto)
                .build();
    }
}