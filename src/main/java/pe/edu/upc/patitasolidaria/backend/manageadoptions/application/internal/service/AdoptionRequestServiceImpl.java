package pe.edu.upc.patitasolidaria.backend.manageadoptions.application.internal.service;

import org.springframework.stereotype.Service;
import pe.edu.upc.patitasolidaria.backend.manageadoptions.domain.model.entity.AdoptionRequest;
import pe.edu.upc.patitasolidaria.backend.manageadoptions.domain.model.enums.RequestStatus;
import pe.edu.upc.patitasolidaria.backend.manageadoptions.infrastructure.persistence.repository.AdoptionRequestRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdoptionRequestServiceImpl implements AdoptionRequestService {

    private final AdoptionRequestRepository repository;

    public AdoptionRequestServiceImpl(AdoptionRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public AdoptionRequest createRequest(AdoptionRequest request) {
        request.setStatus(RequestStatus.PENDING);
        request.setRequestDate(LocalDate.now());
        return repository.save(request);
    }

    @Override
    public List<AdoptionRequest> getAll() {
        return repository.findAll();
    }

    @Override
    public AdoptionRequest getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<AdoptionRequest> getByStatus(RequestStatus status) {
        return repository.findByStatus(status);
    }

    @Override
    public AdoptionRequest updateStatus(Long id, RequestStatus status) {
        AdoptionRequest request = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
        request.setStatus(status);
        return repository.save(request);
    }
}