package com.karolinexo.lab_registry_api.dto;

import com.karolinexo.lab_registry_api.entity.Experiment;
import com.karolinexo.lab_registry_api.enums.Field;
import com.karolinexo.lab_registry_api.enums.Status;

import java.util.UUID;

public record ExperimentSummaryResponse (
        UUID id,
        String title,
        Field field,
        String researcher,
        Status status,
        Boolean active,
        Boolean peerReviewed
) {
    public static ExperimentSummaryResponse from(Experiment experiment){
        return new ExperimentSummaryResponse(
                experiment.getId(),
                experiment.getTitle(),
                experiment.getField(),
                experiment.getResearcher(),
                experiment.getStatus(),
                experiment.getActive(),
                experiment.getPeerReviewed()
        );
    }
}
