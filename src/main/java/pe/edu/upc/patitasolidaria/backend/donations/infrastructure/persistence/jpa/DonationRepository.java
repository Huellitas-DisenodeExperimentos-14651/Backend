package pe.edu.upc.patitasolidaria.backend.donations.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.patitasolidaria.backend.donations.domain.model.aggregates.Donation;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

}
