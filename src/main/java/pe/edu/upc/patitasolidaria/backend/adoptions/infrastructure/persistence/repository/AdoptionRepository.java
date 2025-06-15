package pe.edu.upc.patitasolidaria.backend.adoptions.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.patitasolidaria.backend.adoptions.domain.model.entity.Adoption;

import java.util.List;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
    List<Adoption> findByVaccinationRecordVaccinesContainingIgnoreCase(String vaccine);
}
