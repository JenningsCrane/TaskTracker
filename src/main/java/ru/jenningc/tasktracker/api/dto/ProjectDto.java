package ru.jenningc.tasktracker.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {

    @NonNull
    private Long id;

    @NonNull
    private String name;

    @NonNull
    @JsonProperty("updated_at")
    private Instant updatedAt;

    @NonNull
    @JsonProperty("created_at")
    private Instant createdAt;
}
