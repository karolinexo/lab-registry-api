package com.karolinexo.lab_registry_api.controller;

import com.karolinexo.lab_registry_api.dto.ExperimentRequest;
import com.karolinexo.lab_registry_api.dto.ExperimentResponse;
import com.karolinexo.lab_registry_api.dto.ExperimentSummaryResponse;
import com.karolinexo.lab_registry_api.enums.Field;
import com.karolinexo.lab_registry_api.enums.Status;
import com.karolinexo.lab_registry_api.service.ExperimentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/experiments")
@RequiredArgsConstructor
public class ExperimentController {
    private final ExperimentService service;

    @PostMapping
    public ResponseEntity<ExperimentResponse> create(@RequestBody @Valid ExperimentRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<ExperimentSummaryResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperimentResponse> findById(@PathVariable UUID id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/field/{field}")
    public ResponseEntity<List<ExperimentSummaryResponse>> findByField(@PathVariable Field field){
        return ResponseEntity.ok(service.findByField(field));
    }

    @GetMapping("/peer-reviewed")
    public ResponseEntity<List<ExperimentSummaryResponse>> findPeerReviewed(){
        return ResponseEntity.ok(service.findPeerReviewed());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExperimentResponse> update (@PathVariable UUID id, @RequestBody @Valid ExperimentRequest request){
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExperimentResponse> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<ExperimentResponse> toggleActive (@PathVariable UUID id){
        return ResponseEntity.ok(service.toggleActive(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ExperimentResponse> updateStatus (@PathVariable UUID id, @RequestBody Map<String, String> body){
        Status newStatus = Status.valueOf(body.get("status"));
        return ResponseEntity.ok(service.updateStatus(id, newStatus));
    }


}
