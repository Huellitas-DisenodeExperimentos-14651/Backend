package pe.edu.upc.patitasolidaria.backend.donations.application.internal.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.patitasolidaria.backend.donations.domain.model.aggregates.Donation;
import pe.edu.upc.patitasolidaria.backend.donations.infrastructure.persistence.jpa.DonationRepository;

import java.util.List;

@Service
public class DonationQueryServiceImpl implements DonationQueryService {

    private final DonationRepository donationRepository;

    @Autowired
    public DonationQueryServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }
}
