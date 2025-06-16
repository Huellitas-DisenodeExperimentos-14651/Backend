package pe.edu.upc.patitasolidaria.backend.publications.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.patitasolidaria.backend.publications.domain.model.aggregates.Publication;
import pe.edu.upc.patitasolidaria.backend.publications.domain.model.queries.*;
import pe.edu.upc.patitasolidaria.backend.publications.domain.services.PublicationQueryService;
import pe.edu.upc.patitasolidaria.backend.publications.infrastructure.persistence.jpa.repositories.PublicationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationQueryServiceImpl implements PublicationQueryService {

    private final PublicationRepository publicationRepository;

    public PublicationQueryServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    @Override
    public List<Publication> handle(GetAllPublicationsQuery query) {
        return publicationRepository.findAll();
    }

    @Override
    public Optional<Publication> handle(GetPublicationByIdQuery query) {
        return publicationRepository.findById(query.id());
    }

    @Override
    public List<Publication> handle(GetPublicationsByPetIdQuery query) {
        return publicationRepository.findByPetId(query.petId());
    }
}
