package pe.edu.upc.patitasolidaria.backend.donations.application.internal.queries;

import java.util.List;

import pe.edu.upc.patitasolidaria.backend.donations.domain.model.aggregates.Donation;
import pe.edu.upc.patitasolidaria.backend.donations.domain.model.queries.GetAllDonationsQuery;
import pe.edu.upc.patitasolidaria.backend.donations.domain.model.queries.GetDonationCampaignsQuery;

public interface DonationQueryService {
    List<Donation> handle(GetAllDonationsQuery query);
    List<Donation> handle(GetDonationCampaignsQuery query); // 👈 Este es el nuevo método
}
