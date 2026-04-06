package com.karolinexo.lab_registry_api.repository;

import com.karolinexo.lab_registry_api.entity.Experiment;
import com.karolinexo.lab_registry_api.enums.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExperimentRepository extends JpaRepository<Experiment, UUID> {

    List<Experiment> findByField(Field field);
    List<Experiment> findByPeerReviewedTrue();
    List<Experiment> findByTitleContainingIgnoreCaseOrFieldContainingIgnoreCase(String title, Field field);

}
