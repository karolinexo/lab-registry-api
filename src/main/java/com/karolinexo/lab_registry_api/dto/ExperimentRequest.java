package com.karolinexo.lab_registry_api.dto;

import com.karolinexo.lab_registry_api.enums.Field;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record ExperimentRequest (
        @NotBlank(message = "Title is required")
        String title,

        @NotNull(message = "Field is required")
        Field field,

        @NotBlank(message = "Researcher is required")
        String researcher,

        String hypothesis,

        LocalDate startedAt,

        LocalDate concludedAt,

        @NotNull(message = "Peer reviewed status is required")
        Boolean peerReviewed

) {}
