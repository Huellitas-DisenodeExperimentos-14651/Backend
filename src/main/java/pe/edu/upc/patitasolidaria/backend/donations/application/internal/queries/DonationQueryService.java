package pe.edu.upc.patitasolidaria.backend.donations.application.internal.queries;


import pe.edu.upc.patitasolidaria.backend.donations.domain.model.aggregates.Donation;

import java.util.List;

public interface DonationQueryService {
    List<Donation> getAllDonations();
}
