package ru.jenningc.tasktracker.api.controllers.helpers;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.jenningc.tasktracker.api.exceptions.NotFoundException;
import ru.jenningc.tasktracker.store.entities.ProjectEntity;
import ru.jenningc.tasktracker.store.repositories.ProjectRepository;

@RequiredArgsConstructor
@Component
@Transactional
public class ControllerHelper {

    private final ProjectRepository projectRepository;

    public ProjectEntity getProjectOrThrowException(Long projectId) {
        return projectRepository
                .findById(projectId)
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format(
                                        "Project with id \"%s\" doesn't exist.",
                                        projectId
                                )
                        )
                );
    }
}
