package com.karolinexo.lab_registry_api.exception;

import com.karolinexo.lab_registry_api.enums.Status;

public class InvalidStatusTransitionException  extends RuntimeException {
    public InvalidStatusTransitionException(Status current, Status next) {
        super("Invalid status transition from" + current + " to " + next);
    }
}
