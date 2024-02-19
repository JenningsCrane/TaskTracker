package ru.jenningc.tasktracker.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jenningc.tasktracker.store.entities.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

}
