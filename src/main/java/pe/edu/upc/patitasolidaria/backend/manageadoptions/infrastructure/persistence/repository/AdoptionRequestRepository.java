package pe.edu.upc.patitasolidaria.backend.manageadoptions.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.patitasolidaria.backend.manageadoptions.domain.model.entity.AdoptionRequest;
import pe.edu.upc.patitasolidaria.backend.manageadoptions.domain.model.enums.RequestStatus;

import java.util.List;

public interface AdoptionRequestRepository extends JpaRepository<AdoptionRequest, Long> {
    List<AdoptionRequest> findByStatus(RequestStatus status);
}