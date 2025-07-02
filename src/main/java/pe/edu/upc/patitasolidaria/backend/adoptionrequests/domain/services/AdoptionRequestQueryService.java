package pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.services;

import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.aggregates.AdoptionRequest;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface AdoptionRequestQueryService {

    List<AdoptionRequest> handle(GetAllAdoptionRequestsQuery query);

    List<AdoptionRequest> handle(GetAdoptionRequestsByApplicantIdQuery query);

    List<AdoptionRequest> handle(GetAdoptionRequestsByPublicationIdQuery query);

    Optional<AdoptionRequest> handle(GetAdoptionRequestByIdQuery query); // Si deseas consultar una por ID
}
