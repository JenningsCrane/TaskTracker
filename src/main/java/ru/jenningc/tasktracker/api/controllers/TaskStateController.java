package ru.jenningc.tasktracker.api.controllers;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.jenningc.tasktracker.api.controllers.helpers.ControllerHelper;
import ru.jenningc.tasktracker.api.dto.TaskStateDto;
import ru.jenningc.tasktracker.api.exceptions.BadRequestException;
import ru.jenningc.tasktracker.api.factories.TaskStateDtoFactory;
import ru.jenningc.tasktracker.store.entities.ProjectEntity;
import ru.jenningc.tasktracker.store.entities.TaskStateEntity;
import ru.jenningc.tasktracker.store.repositories.TaskStateRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@RestController
public class TaskStateController {

    private final ControllerHelper controllerHelper;
    private final TaskStateRepository taskStateRepository;
    private final TaskStateDtoFactory taskStateDtoFactory;

    public static final String DELETE_TASK_STATES = "/api/projects/{project_id}";
    public static final String GET_TASK_STATES = "/api/projects/{project_id}/task-states";
    public static final String UPDATE_TASK_STATE = "/api/projects/{project_id}/task-states";
    public static final String CREATE_TASK_STATE = "/api/projects/{project_id}/task-states";

    @GetMapping(GET_TASK_STATES)
    public List<TaskStateDto> getTaskStates(@PathVariable(name = "project_id") Long projectId) {

        ProjectEntity project = controllerHelper.getProjectOrThrowException(projectId);

        return project
                .getTaskStates()
                .stream()
                .map(taskStateDtoFactory::makeTaskStateDto)
                .collect(Collectors.toList());
    }

    @PostMapping(CREATE_TASK_STATE)
    public TaskStateDto createTaskState(
            @PathVariable(name = "project_id") Long projectId,
            @RequestParam(name = "task_state_name") String taskStateName) {

        if (taskStateName.trim().isEmpty()) {
            throw new BadRequestException("Task state name can't be empty");
        }

        ProjectEntity project = controllerHelper.getProjectOrThrowException(projectId);

        project
                .getTaskStates()
                .stream()
                .map(TaskStateEntity::getName)
                .filter(anotherTaskStateName -> anotherTaskStateName.equalsIgnoreCase(taskStateName))
                .findAny()
                .ifPresent(it -> {
                    throw new BadRequestException(String.format("Task state \"%s\" already exists", taskStateName));
                });

        TaskStateEntity taskState = taskStateRepository.saveAndFlush(
                TaskStateEntity.builder()
                        .name(taskStateName)
                        .build()
        );

        taskStateRepository
                .findTaskStateEntityByRightTaskStateIdIsNullAndProjectId(projectId)
                .ifPresent(anotherTaskState -> {

                    taskState.setLeftTaskState(anotherTaskState);

                    anotherTaskState.setRightTaskState(taskState);

                    taskStateRepository.saveAndFlush(anotherTaskState);
                });

        final TaskStateEntity savedTaskState = taskStateRepository.saveAndFlush(taskState);

        return taskStateDtoFactory.makeTaskStateDto(savedTaskState);

    }


}
