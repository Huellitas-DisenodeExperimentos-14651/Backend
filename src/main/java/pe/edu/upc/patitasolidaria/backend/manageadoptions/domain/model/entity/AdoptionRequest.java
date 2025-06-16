package pe.edu.upc.patitasolidaria.backend.manageadoptions.domain.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import pe.edu.upc.patitasolidaria.backend.manageadoptions.domain.model.enums.RequestStatus;
import pe.edu.upc.patitasolidaria.backend.manageadoptions.domain.model.valueobject.Applicant;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "adoptions_requests")
public class AdoptionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long adoptionId;

    @Embedded
    private Applicant applicant;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private LocalDate requestDate;

    private String reasonMessage;
}