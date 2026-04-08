package com.karolinexo.lab_registry_api.service;

import com.karolinexo.lab_registry_api.dto.ExperimentRequest;
import com.karolinexo.lab_registry_api.dto.ExperimentResponse;
import com.karolinexo.lab_registry_api.dto.ExperimentSummaryResponse;
import com.karolinexo.lab_registry_api.entity.Experiment;
import com.karolinexo.lab_registry_api.enums.Field;
import com.karolinexo.lab_registry_api.enums.Status;
import com.karolinexo.lab_registry_api.exception.ExperimentNotFoundException;
import com.karolinexo.lab_registry_api.exception.InvalidStatusTransitionException;
import com.karolinexo.lab_registry_api.repository.ExperimentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExperimentService {

    private final ExperimentRepository repository;

    public ExperimentResponse create(ExperimentRequest request){
        Experiment experiment = Experiment.builder()
                .title(request.title())
                .field(request.field())
                .researcher(request.researcher())
                .hypothesis(request.hypothesis())
                .status(Status.PLANNED)
                .active(true)
                .peerReviewed(request.peerReviewed())
                .startedAt(request.startedAt())
                .concludedAt(request.concludedAt())
                .build();

        return ExperimentResponse.from(repository.save(experiment));
    }

    public List<ExperimentSummaryResponse> findAll(){
        return repository.findAll()
                .stream()
                .map(ExperimentSummaryResponse::from)
                .toList();
    }

    public ExperimentResponse findById(UUID id){
        Experiment experiment = findExperimentById(id);
        return ExperimentResponse.from(experiment);
    }

    public List<ExperimentSummaryResponse> findByField(Field field){
        return repository.findByField(field)
                .stream()
                .map(ExperimentSummaryResponse::from)
                .toList();
    }

    public ExperimentResponse update(UUID id, ExperimentRequest request){
        Experiment experiment = findExperimentById(id);

        experiment.setTitle(request.title());
        experiment.setField(request.field());
        experiment.setResearcher(request.researcher());
        experiment.setHypothesis(request.hypothesis());
        experiment.setStartedAt(request.startedAt());
        experiment.setConcludedAt(request.concludedAt());
        experiment.setPeerReviewed(request.peerReviewed());

        return ExperimentResponse.from(repository.save(experiment));
    }

    public void delete(UUID id){
        findExperimentById(id);
        repository.deleteById(id);
    }

    public ExperimentResponse toggleActive(UUID id){
        Experiment experiment = findExperimentById(id);
        experiment.setActive(!experiment.getActive());
        return ExperimentResponse.from(repository.save(experiment));
    }

    public ExperimentResponse updateStatus(UUID id, Status newStatus){
        Experiment experiment = findExperimentById(id);
        validateStatusTransition(experiment, newStatus);
        experiment.setStatus(newStatus);
        return ExperimentResponse.from(repository.save(experiment));
    }

    private void validateStatusTransition(Experiment experiment, Status newStatus){
        Status current = experiment.getStatus();

        boolean valid = switch (current){
            case PLANNED -> newStatus == Status.IN_PROGRESS;
            case IN_PROGRESS -> newStatus == Status.CONCLUDED || newStatus == Status.FAILED;
            case CONCLUDED -> newStatus == Status.REPLICATED;
            case FAILED, REPLICATED -> false;
        };

        if (!valid){
            throw new InvalidStatusTransitionException(current, newStatus);
        }

        if (newStatus == Status.IN_PROGRESS && experiment.getStartedAt() == null){
            throw new IllegalStateException("Cannot start experiment without a startedAt date");
        }

        if (newStatus == Status.CONCLUDED && experiment.getConcludedAt() == null){
            throw new IllegalStateException("Cannot conclude experiment without a concludedAt date");
        }
    }

    private Experiment findExperimentById(UUID id){
        return repository.findById(id)
                .orElseThrow(() -> new ExperimentNotFoundException(id));
    }

    public List<ExperimentSummaryResponse> findPeerReviewed(){
        return repository.findByPeerReviewedTrue()
                .stream()
                .map(ExperimentSummaryResponse::from)
                .toList();
    }
}
