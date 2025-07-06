package pe.edu.upc.patitasolidaria.backend.donations.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.upc.patitasolidaria.backend.donations.application.internal.commandservices.DonationCommandService;
import pe.edu.upc.patitasolidaria.backend.donations.application.internal.queries.DonationQueryService;
import pe.edu.upc.patitasolidaria.backend.donations.domain.model.aggregates.Donation;
import pe.edu.upc.patitasolidaria.backend.donations.domain.model.commands.CreateDonationCommand;
import pe.edu.upc.patitasolidaria.backend.donations.domain.model.commands.UpdateDonationCommand;
import pe.edu.upc.patitasolidaria.backend.donations.domain.model.queries.GetAllDonationsQuery;
import pe.edu.upc.patitasolidaria.backend.donations.domain.model.queries.GetDonationCampaignsQuery;

import io.swagger.v3.oas.annotations.tags.Tag;

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

    @GetMapping
    public ResponseEntity<List<Donation>> getAllDonations() {
        GetAllDonationsQuery query = new GetAllDonationsQuery();
        return ResponseEntity.ok(queryService.handle(query));
    }

    @GetMapping("/campaigns")
    public ResponseEntity<List<Donation>> getDonationCampaigns() {
        GetDonationCampaignsQuery query = new GetDonationCampaignsQuery();
        List<Donation> campaigns = queryService.handle(query);
        return ResponseEntity.ok(campaigns);
    }

    // Se eliminó el @PreAuthorize temporalmente para pruebas
    @PostMapping
    public ResponseEntity<Donation> createDonation(@RequestBody CreateDonationCommand command) {
        Donation donation = commandService.createDonation(command);
        return new ResponseEntity<>(donation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donation> updateDonation(@PathVariable Long id, @RequestBody UpdateDonationCommand command) {
        Donation updated = commandService.updateDonation(id, command);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonation(@PathVariable Long id) {
        commandService.deleteDonation(id);
        return ResponseEntity.noContent().build();
    }
}


