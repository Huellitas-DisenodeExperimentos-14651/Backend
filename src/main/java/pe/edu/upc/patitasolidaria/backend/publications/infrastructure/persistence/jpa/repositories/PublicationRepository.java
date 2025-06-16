package pe.edu.upc.patitasolidaria.backend.publications.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.patitasolidaria.backend.publications.domain.model.aggregates.Publication;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    List<Publication> findByPetId(Long petId); // Si deseas filtrar publicaciones por mascota relacionada
}
