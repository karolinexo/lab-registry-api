package com.karolinexo.lab_registry_api;

import com.karolinexo.lab_registry_api.entity.Experiment;
import com.karolinexo.lab_registry_api.enums.Field;
import com.karolinexo.lab_registry_api.enums.Status;
import com.karolinexo.lab_registry_api.repository.ExperimentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final ExperimentRepository repository;

    @Override
    public void run(String... args){
        if (repository.count() > 0) return;

        repository.saveAll(List.of(
                Experiment.builder()
                        .title("spontaneous Generation Disproof")
                        .field(Field.BIOLOGY)
                        .researcher("Louis Pasteur")
                        .hypothesis("Microorganisms come from the air, not from sppontaneous generation")
                        .status(Status.REPLICATED)
                        .active(true)
                        .peerReviewed(true)
                        .startedAt(LocalDate.of(1859, 1, 1))
                        .concludedAt(LocalDate.of(1859, 12, 31))
                        .build(),

                Experiment.builder()
                        .title("Bacterial Transformation")
                        .field(Field.BIOLOGY)
                        .researcher("Frederick Griffith")
                        .hypothesis("Dead bacteria can transfer genetic material to living bacteria")
                        .status(Status.REPLICATED)
                        .active(true)
                        .peerReviewed(true)
                        .startedAt(LocalDate.of(1928, 1, 1))
                        .concludedAt(LocalDate.of(1928, 12, 31))
                        .build(),

                Experiment.builder()
                        .title("Miller-Urey Prebiotic Synthesis")
                        .field(Field.CHEMISTRY)
                        .researcher("Stanley Miller and Harold Urey")
                        .hypothesis("Amino acids can form spontaneously from inorganic compounds under early Earth conditions")
                        .status(Status.REPLICATED)
                        .active(true)
                        .peerReviewed(true)
                        .startedAt(LocalDate.of(1953, 1, 1))
                        .concludedAt(LocalDate.of(1953, 12, 31))
                        .build(),

                Experiment.builder()
                        .title("Ozone Discovery")
                        .field(Field.CHEMISTRY)
                        .researcher("Christian Friedrich Schönbein")
                        .hypothesis("An unknown gas is produced during water electrolysis")
                        .status(Status.CONCLUDED)
                        .active(true)
                        .peerReviewed(true)
                        .startedAt(LocalDate.of(1839, 1, 1))
                        .concludedAt(LocalDate.of(1839, 12, 31))
                        .build(),

                Experiment.builder()
                        .title("Double-Slit Light Interference")
                        .field(Field.PHYSICS)
                        .researcher("Thomas Young")
                        .hypothesis("Light behaves as a wave and produces interference patterns")
                        .status(Status.REPLICATED)
                        .active(true)
                        .peerReviewed(true)
                        .startedAt(LocalDate.of(1801, 1, 1))
                        .concludedAt(LocalDate.of(1801, 12, 31))
                        .build(),

                Experiment.builder()
                        .title("Luminiferous Aether Detection Attempt")
                        .field(Field.PHYSICS)
                        .researcher("Albert Michelson and Edward Morley")
                        .hypothesis("The luminiferous aether can be detected by measuring differences in light speed")
                        .status(Status.CONCLUDED)
                        .active(true)
                        .peerReviewed(true)
                        .startedAt(LocalDate.of(1887, 1, 1))
                        .concludedAt(LocalDate.of(1887, 12, 31))
                        .build(),

                Experiment.builder()
                        .title("Classical Conditioning in Dogs")
                        .field(Field.NEUROSCIENCE)
                        .researcher("Ivan Pavlov")
                        .hypothesis("Animals can be conditioned to associate neutral stimuli with natural reflexes")
                        .status(Status.REPLICATED)
                        .active(true)
                        .peerReviewed(true)
                        .startedAt(LocalDate.of(1897, 1, 1))
                        .concludedAt(LocalDate.of(1897, 12, 31))
                        .build(),

                Experiment.builder()
                        .title("Free Will and Neural Activity")
                        .field(Field.NEUROSCIENCE)
                        .researcher("Benjamin Libet")
                        .hypothesis("Brain activity precedes conscious awareness of the decision to move")
                        .status(Status.IN_PROGRESS)
                        .active(true)
                        .peerReviewed(true)
                        .startedAt(LocalDate.of(1983, 1, 1))
                        .build(),

                Experiment.builder()
                        .title("Laws of Heredity")
                        .field(Field.GENETICS)
                        .researcher("Gregor Mendel")
                        .hypothesis("Traits are inherited through discrete units following predictable patterns")
                        .status(Status.REPLICATED)
                        .active(true)
                        .peerReviewed(true)
                        .startedAt(LocalDate.of(1856, 1, 1))
                        .concludedAt(LocalDate.of(1863, 12, 31))
                        .build(),

                Experiment.builder()
                        .title("Human Genome Mapping")
                        .field(Field.GENETICS)
                        .researcher("International Human Genome Sequencing Consortium")
                        .hypothesis("The complete sequence of human DNA can be mapped and decoded")
                        .status(Status.CONCLUDED)
                        .active(true)
                        .peerReviewed(true)
                        .startedAt(LocalDate.of(1990, 10, 1))
                        .concludedAt(LocalDate.of(2003, 4, 12))
                        .build(),

                Experiment.builder()
                        .title("Gravitacional Waves Detection")
                        .field(Field.ASTRONOMY)
                        .researcher("LIGO Scientific Collaboration")
                        .hypothesis("Gravitational waves predicted by Einstein can be directly detected")
                        .status(Status.CONCLUDED)
                        .active(true)
                        .peerReviewed(true)
                        .startedAt(LocalDate.of(2015, 9, 14))
                        .concludedAt(LocalDate.of(2016, 2, 11))
                        .build(),

                Experiment.builder()
                        .title("Jupiter Moons Observation")
                        .field(Field.ASTRONOMY)
                        .researcher("Galileo Galilei")
                        .hypothesis("Jupiter has moons orbiting it, proving not everything orbits Earth")
                        .status(Status.CONCLUDED)
                        .active(true)
                        .peerReviewed(false)
                        .startedAt(LocalDate.of(1610, 1, 7))
                        .concludedAt(LocalDate.of(1610, 3, 2))
                        .build(),

                Experiment.builder()
                        .title("Quantum Tunneling in Enzyme Catalysis")
                        .field(Field.CHEMISTRY)
                        .researcher("Judith Klinman")
                        .hypothesis("Enzymes exploit quantum tunneling to accelerate hydrogen transfer reactions")
                        .status(Status.IN_PROGRESS)
                        .active(true)
                        .peerReviewed(true)
                        .startedAt(LocalDate.of(2020, 3, 1))
                        .build(),

                Experiment.builder()
                        .title("CRISPR Gene Editing Efficiency")
                        .field(Field.GENETICS)
                        .researcher("Jennifer Doudna and Emmanuelle Charpentier")
                        .hypothesis("CRISPR-Cas9 can be used as a precise tool for genome editing in any organism")
                        .status(Status.REPLICATED)
                        .active(true)
                        .peerReviewed(true)
                        .startedAt(LocalDate.of(2012, 1, 1))
                        .concludedAt(LocalDate.of(2012, 12, 31))
                        .build()
        ));
    }
}
