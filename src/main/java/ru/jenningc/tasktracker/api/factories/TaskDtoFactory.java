package ru.jenningc.tasktracker.api.factories;

import org.springframework.stereotype.Component;
import ru.jenningc.tasktracker.api.dto.TaskDto;
import ru.jenningc.tasktracker.store.entities.TaskEntity;

@Component
public class TaskDtoFactory {

    public TaskDto makeTaskDto(TaskEntity entity) {

        return TaskDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .description(entity.getDescription())
                .build();
    }
}
