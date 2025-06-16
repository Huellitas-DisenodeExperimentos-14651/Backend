package pe.edu.upc.patitasolidaria.backend.manageadoptions.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.patitasolidaria.backend.manageadoptions.application.internal.service.AdoptionRequestService;
import pe.edu.upc.patitasolidaria.backend.manageadoptions.domain.model.entity.AdoptionRequest;
import pe.edu.upc.patitasolidaria.backend.manageadoptions.domain.model.enums.RequestStatus;

import java.util.List;

@RestController
@RequestMapping("/api/adoption-requests")
@CrossOrigin(origins = "*")
@Tag(name = "Adoption Requests", description = "Manage Adoptions Request Endpoints")
public class AdoptionRequestController {

    private final AdoptionRequestService adoptionRequestService;

    @Autowired
    public AdoptionRequestController(AdoptionRequestService adoptionRequestService) {
        this.adoptionRequestService = adoptionRequestService;
    }

    @PostMapping
    @Operation(summary = "Create a new adoption request")
    public AdoptionRequest createRequest(@RequestBody AdoptionRequest request) {
        return adoptionRequestService.createRequest(request);
    }

    @GetMapping
    @Operation(summary = "Get all adoption requests or filter by status")
    public List<AdoptionRequest> getAllRequests(@RequestParam(required = false) RequestStatus status) {
        if (status != null) {
            return adoptionRequestService.getByStatus(status);
        }
        return adoptionRequestService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get adoption request by ID")
    public AdoptionRequest getRequestById(@PathVariable Long id) {
        return adoptionRequestService.getById(id);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update the status of a request (ACCEPTED or REJECTED)")
    public AdoptionRequest updateStatus(@PathVariable Long id, @RequestParam RequestStatus status) {
        return adoptionRequestService.updateStatus(id, status);
    }
}