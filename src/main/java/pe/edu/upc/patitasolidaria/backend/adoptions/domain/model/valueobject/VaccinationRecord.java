package pe.edu.upc.patitasolidaria.backend.adoptions.domain.model.valueobject;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Embeddable
@Data
public class VaccinationRecord {

    private LocalDate recordDate;
    private LocalDate lastVaccinationDate;
    private String observations;

    @ElementCollection
    @CollectionTable(
            name = "adoptions_vaccines",
            joinColumns = @JoinColumn(name = "record_id")
    )
    @Column(name = "vaccine")
    private List<String> vaccines;
}
