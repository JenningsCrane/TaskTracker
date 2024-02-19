package ru.jenningc.tasktracker.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jenningc.tasktracker.store.entities.TaskStateEntity;

import java.util.Optional;

public interface TaskStateRepository extends JpaRepository<TaskStateEntity, Long> {
    Optional<TaskStateEntity> findTaskStateEntityByRightTaskStateIdIsNullAndProjectId(Long projectId);
}
