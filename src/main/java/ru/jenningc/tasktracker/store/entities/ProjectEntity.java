package ru.jenningc.tasktracker.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String name;

    @Builder.Default
    private Instant updatedAt = Instant.now();

    @Builder.Default
    private Instant createdAt = Instant.now();



    @Builder.Default
    @OneToMany
    @JoinColumn(name = "project_id")
    private List<TaskStateEntity> taskStates = new ArrayList<>();
}
