package pe.edu.upc.patitasolidaria.backend.pets.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.patitasolidaria.backend.pets.domain.model.aggregates.Pet;
import pe.edu.upc.patitasolidaria.backend.pets.domain.model.valueobjects.PetStatus;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByStatus(PetStatus status);
    List<Pet> findByProfileId(Long profileId);
}
