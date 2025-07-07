package pe.edu.upc.patitasolidaria.backend.donations.application.internal.queries;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.patitasolidaria.backend.donations.domain.model.aggregates.Donation;
import pe.edu.upc.patitasolidaria.backend.donations.domain.model.queries.GetAllDonationsQuery;
import pe.edu.upc.patitasolidaria.backend.donations.domain.model.queries.GetDonationCampaignsQuery;
import pe.edu.upc.patitasolidaria.backend.donations.application.internal.queries.DonationQueryService;
import pe.edu.upc.patitasolidaria.backend.donations.infrastructure.persistence.jpa.DonationRepository;

import pe.edu.upc.patitasolidaria.backend.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import pe.edu.upc.patitasolidaria.backend.profiles.domain.model.aggregates.Profile;

@Service
public class DonationQueryServiceImpl implements DonationQueryService {

    private final DonationRepository donationRepository;
    private final ProfileRepository profileRepository;

    @Autowired
    public DonationQueryServiceImpl(DonationRepository donationRepository, ProfileRepository profileRepository) {
        this.donationRepository = donationRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public List<Donation> handle(GetAllDonationsQuery query) {
        return donationRepository.findAll();
    }

    @Override
    public List<Donation> handle(GetDonationCampaignsQuery query) {
        return profileRepository.findAll().stream()
                .filter(profile -> "SHELTER".equalsIgnoreCase(profile.getRole().name()))
                .map(profile -> {
                    Donation campaign = new Donation();
                    campaign.setType("monetaria");
                    campaign.setTitle("Campaña de " + profile.getName());
                    campaign.setDescription("Apoya al refugio " + profile.getName());
                    campaign.setContactInfo(profile.getEmail().toString());
                    campaign.setAddress(profile.getAddress().toString());
                    campaign.setImageUrl(profile.getProfilePic());
                    return campaign;
                })
                .collect(Collectors.toList());
    }

}
