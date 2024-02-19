package ru.jenningc.tasktracker.api.dto;


import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    @NonNull
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private Instant createdAt;

    @NonNull
    private String description;
}
