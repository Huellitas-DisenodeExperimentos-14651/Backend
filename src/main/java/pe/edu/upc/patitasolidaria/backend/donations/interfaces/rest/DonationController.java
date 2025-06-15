package pe.edu.upc.patitasolidaria.backend.donations.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.patitasolidaria.backend.donations.application.internal.commandservices.DonationCommandService;
import pe.edu.upc.patitasolidaria.backend.donations.application.internal.queries.DonationQueryService;
import pe.edu.upc.patitasolidaria.backend.donations.domain.model.aggregates.Donation;
import pe.edu.upc.patitasolidaria.backend.donations.domain.model.commands.CreateDonationCommand;

import java.util.List;

@RestController
@RequestMapping("/api/v1/donations")
@Tag(name = "Donations", description = "Donation management endpoints")
public class DonationController {

    private final DonationCommandService commandService;
    private final DonationQueryService queryService;

    public DonationController(DonationCommandService commandService, DonationQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<Donation> createDonation(@Valid @RequestBody CreateDonationCommand command) {
        Donation donation = commandService.createDonation(command);
        return new ResponseEntity<>(donation, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Donation>> getAllDonations() {
        List<Donation> donations = queryService.getAllDonations();
        return new ResponseEntity<>(donations, HttpStatus.OK);
    }
}

