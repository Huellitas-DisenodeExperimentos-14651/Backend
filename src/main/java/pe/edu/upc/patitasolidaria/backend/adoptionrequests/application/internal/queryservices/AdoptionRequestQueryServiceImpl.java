package pe.edu.upc.patitasolidaria.backend.adoptionrequests.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.aggregates.AdoptionRequest;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.queries.*;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.services.AdoptionRequestQueryService;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.infrastructure.persistence.jpa.repositories.AdoptionRequestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdoptionRequestQueryServiceImpl implements AdoptionRequestQueryService {

    private final AdoptionRequestRepository adoptionRequestRepository;

    public AdoptionRequestQueryServiceImpl(AdoptionRequestRepository adoptionRequestRepository) {
        this.adoptionRequestRepository = adoptionRequestRepository;
    }

    @Override
    public List<AdoptionRequest> handle(GetAllAdoptionRequestsQuery query) {
        return adoptionRequestRepository.findAll();
    }

    @Override
    public Optional<AdoptionRequest> handle(GetAdoptionRequestByIdQuery query) {
        return adoptionRequestRepository.findById(query.id());
    }

    @Override
    public List<AdoptionRequest> handle(GetAdoptionRequestsByPublicationIdQuery query) {
        return adoptionRequestRepository.findByPublicationId(query.publicationId());
    }

    @Override
    public List<AdoptionRequest> handle(GetAdoptionRequestsByApplicantIdQuery query) {
        return adoptionRequestRepository.findByApplicant_Id(query.applicantId());
    }
}
