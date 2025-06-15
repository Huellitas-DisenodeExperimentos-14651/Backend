package pe.edu.upc.patitasolidaria.backend.donations.application.internal.commandservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.patitasolidaria.backend.donations.domain.model.aggregates.Donation;
import pe.edu.upc.patitasolidaria.backend.donations.domain.model.commands.CreateDonationCommand;
import pe.edu.upc.patitasolidaria.backend.donations.infrastructure.persistence.jpa.DonationRepository;

@Service
public class DonationCommandServiceImpl implements DonationCommandService {

    private final DonationRepository donationRepository;

    @Autowired
    public DonationCommandServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public Donation createDonation(CreateDonationCommand command) {
        Donation donation = new Donation(
                command.amount(),
                command.paymentMethod(),
                command.transactionNumber(),
                command.donorName()
        );

        return donationRepository.save(donation);
    }
}
