package pe.edu.upc.patitasolidaria.backend.adoptionrequests.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.aggregates.AdoptionRequest;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.valueobjects.RequestStatus;

import java.util.List;

@Repository
public interface AdoptionRequestRepository extends JpaRepository<AdoptionRequest, Long> {

    // Obtener todas las solicitudes para una publicación específica
    List<AdoptionRequest> findByPublicationId(Long publicationId);

    // Obtener todas las solicitudes por estado (PENDING, APPROVED, REJECTED)
    List<AdoptionRequest> findByStatus(RequestStatus status);

    // Verifica si ya existe una solicitud por parte del mismo solicitante
    boolean existsByPublicationIdAndApplicant_Id(Long publicationId, Long applicantId);

    List<AdoptionRequest> findByApplicant_Id(Long applicantId);
}
