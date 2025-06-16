package pe.edu.upc.patitasolidaria.backend.manageadoptions.application.internal.service;

import pe.edu.upc.patitasolidaria.backend.manageadoptions.domain.model.entity.AdoptionRequest;
import pe.edu.upc.patitasolidaria.backend.manageadoptions.domain.model.enums.RequestStatus;

import java.util.List;

public interface AdoptionRequestService {
    AdoptionRequest createRequest(AdoptionRequest request);
    List<AdoptionRequest> getAll();
    AdoptionRequest getById(Long id);
    List<AdoptionRequest> getByStatus(RequestStatus status);
    AdoptionRequest updateStatus(Long id, RequestStatus status);
}