package pe.edu.upc.patitasolidaria.backend.adoptionrequests.interfaces.rest;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.commands.ApproveAdoptionRequestCommand;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.commands.CreateAdoptionRequestCommand;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.commands.RejectAdoptionRequestCommand;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.queries.GetAdoptionRequestByIdQuery;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.queries.GetAllAdoptionRequestsQuery;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.services.AdoptionRequestCommandService;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.services.AdoptionRequestQueryService;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.interfaces.rest.resources.AdoptionRequestResource;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.interfaces.rest.resources.CreateAdoptionRequestResource;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.interfaces.rest.transform.AdoptionRequestResourceFromEntityAssembler;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.interfaces.rest.transform.CreateAdoptionRequestCommandFromResourceAssembler;
import pe.edu.upc.patitasolidaria.backend.iam.domain.model.aggregates.JwtUserDetails;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET})
@RestController
@RequestMapping(value = "/api/v1/adoption-requests", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Adoption Requests", description = "Adoption Request Management Endpoints")
public class AdoptionRequestController {

    private final AdoptionRequestCommandService commandService;
    private final AdoptionRequestQueryService queryService;

    public AdoptionRequestController(AdoptionRequestCommandService commandService, AdoptionRequestQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<AdoptionRequestResource> createAdoptionRequest(
            @AuthenticationPrincipal JwtUserDetails userDetails,
            @Valid @RequestBody CreateAdoptionRequestResource resource
    ) {
        Long profileId = userDetails.getProfileId(); // ✅ viene del JWT
        var command = CreateAdoptionRequestCommandFromResourceAssembler.toCommandFromResource(resource, profileId);
        var requestId = commandService.handle(command);

        var request = queryService.handle(new GetAdoptionRequestByIdQuery(requestId)).get();
        var response = AdoptionRequestResourceFromEntityAssembler.toResourceFromEntity(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<AdoptionRequestResource>> getAllAdoptionRequests() {
        var query = new GetAllAdoptionRequestsQuery();
        var requests = queryService.handle(query);

        var resources = requests.stream()
                .map(AdoptionRequestResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{requestId}/approve")
    public ResponseEntity<AdoptionRequestResource> approveRequest(@PathVariable Long requestId) {
        var command = new ApproveAdoptionRequestCommand(requestId);
        commandService.handle(command);

        var updatedRequest = queryService.handle(new GetAdoptionRequestByIdQuery(requestId)).get();
        var resource = AdoptionRequestResourceFromEntityAssembler.toResourceFromEntity(updatedRequest);

        return ResponseEntity.ok(resource);
    }

    @PutMapping("/{requestId}/reject")
    public ResponseEntity<AdoptionRequestResource> rejectRequest(@PathVariable Long requestId) {
        var command = new RejectAdoptionRequestCommand(requestId);
        commandService.handle(command);

        var updatedRequest = queryService.handle(new GetAdoptionRequestByIdQuery(requestId)).get();
        var resource = AdoptionRequestResourceFromEntityAssembler.toResourceFromEntity(updatedRequest);

        return ResponseEntity.ok(resource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdoptionRequestResource> getById(@PathVariable Long id) {
        var query = new GetAdoptionRequestByIdQuery(id);
        var optionalRequest = queryService.handle(query);

        return optionalRequest.map(request ->
                ResponseEntity.ok(AdoptionRequestResourceFromEntityAssembler.toResourceFromEntity(request))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }
}