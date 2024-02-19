package ru.jenningc.tasktracker.api.factories;

import org.springframework.stereotype.Component;
import ru.jenningc.tasktracker.api.dto.ProjectDto;
import ru.jenningc.tasktracker.store.entities.ProjectEntity;

@Component
public class ProjectDtoFactory {

    public ProjectDto makeProjectDto(ProjectEntity entity) {

        return ProjectDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
