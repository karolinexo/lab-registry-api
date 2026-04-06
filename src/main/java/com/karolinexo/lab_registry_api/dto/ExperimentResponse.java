package com.karolinexo.lab_registry_api.dto;

import com.karolinexo.lab_registry_api.entity.Experiment;
import com.karolinexo.lab_registry_api.enums.Field;
import com.karolinexo.lab_registry_api.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ExperimentResponse (
        UUID id,
        String title,
        Field field,
        String researcher,
        String hypothesis,
        Status status,
        Boolean active,
        Boolean peerReviewed,
        LocalDate startedAt,
        LocalDate concludedAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ExperimentResponse from(Experiment experiment){
        return new ExperimentResponse(
                experiment.getId(),
                experiment.getTitle(),
                experiment.getField(),
                experiment.getResearcher(),
                experiment.getHypothesis(),
                experiment.getStatus(),
                experiment.getActive(),
                experiment.getPeerReviewed(),
                experiment.getStartedAt(),
                experiment.getConcludedAt(),
                experiment.getCreatedAt(),
                experiment.getUpdateAt()
        );
    }
}
