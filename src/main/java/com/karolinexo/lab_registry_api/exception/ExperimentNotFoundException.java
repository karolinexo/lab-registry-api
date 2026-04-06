package com.karolinexo.lab_registry_api.exception;

import java.util.UUID;

public class ExperimentNotFoundException extends RuntimeException {

    public ExperimentNotFoundException (UUID id){
        super("Experiment not found with id: " + id);
    }
}
