package com.riwi.riwi_projects_system.projects.aplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.riwi.riwi_projects_system.common.infrastructure.mails.EmailUtils;
import com.riwi.riwi_projects_system.projects.domain.TaskStates;
import com.riwi.riwi_projects_system.projects.domain.TasksEntity;
import com.riwi.riwi_projects_system.projects.domain.TasksRepository;
import com.riwi.riwi_projects_system.projects.infrastructure.dtos.request.CreateTaskDto;

import jakarta.mail.MessagingException;

@Service
public class TasksService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${spring.mail.username}")
    private String mailUsername;

    private final TasksRepository tasksRepository;
    private final EmailUtils emailUtils;

    public TasksService(TasksRepository tasksRepository, EmailUtils emailUtils) {
        this.tasksRepository = tasksRepository;
        this.emailUtils = emailUtils;
    }

    public TasksEntity createTask(CreateTaskDto createTaskDto) {
        TasksEntity tasksEntity = new TasksEntity();
        tasksEntity.setTitle(createTaskDto.getTitle());
        tasksEntity.getState();
        tasksEntity.setDescription(createTaskDto.getDescription());
        return tasksRepository.save(tasksEntity);
    }

    public TasksEntity changeTaskState(Long id, TaskStates newTaskState) {
        var taskToUpdate = findTaskById(id);
        String previousSate = taskToUpdate.getState().name();
        taskToUpdate.setState(newTaskState);
        var updatedTask = this.tasksRepository.save(taskToUpdate);

        // Notify userS about the change
        notifyUsersOfTaskStateChange(updatedTask.getTitle(), previousSate,
                updatedTask.getState().name());

        return updatedTask;
    }

    private TasksEntity findTaskById(Long id) {
        return this.tasksRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND,
                        "Task with id " + id + " not found"));
    }

    private void notifyUsersOfTaskStateChange(String taskTitle, String previousSate,
            String newState) {
        String htmlTemplate = String.format("""
                <h1>Task state changed</h1>;
                "<p>'%s' was changed from '%s' to '%s' state</p>"
                      """, taskTitle, previousSate, newState);
        try {
            // TODO: Change mailUsername for all registered users
            this.emailUtils.sendHtmlEmail(new String[] { mailUsername },
                    "Task state changed!", htmlTemplate);
        } catch (MessagingException | RuntimeException e) {
            this.logger.error("Error sending email", e);
        }
    }

}