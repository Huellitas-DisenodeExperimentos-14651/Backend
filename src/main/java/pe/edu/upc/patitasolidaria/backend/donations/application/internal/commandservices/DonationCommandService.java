package pe.edu.upc.patitasolidaria.backend.donations.application.internal.commandservices;

import pe.edu.upc.patitasolidaria.backend.donations.domain.model.aggregates.Donation;
import pe.edu.upc.patitasolidaria.backend.donations.domain.model.commands.CreateDonationCommand;
import pe.edu.upc.patitasolidaria.backend.donations.domain.model.commands.UpdateDonationCommand;

public interface DonationCommandService {
    Donation createDonation(CreateDonationCommand command);
    Donation updateDonation(Long id, UpdateDonationCommand command);
    void deleteDonation(Long id);
}

