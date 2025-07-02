package pe.edu.upc.patitasolidaria.backend.adoptionrequests.interfaces.rest.resources;

import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.valueobjects.RequestStatus;

import java.time.LocalDate;

public record AdoptionRequestResource(
        Long id,
        Long publicationId,
        Long applicantId,
        String applicantFullName,
        String reasonMessage,
        RequestStatus status,
        LocalDate requestDate
) {}
