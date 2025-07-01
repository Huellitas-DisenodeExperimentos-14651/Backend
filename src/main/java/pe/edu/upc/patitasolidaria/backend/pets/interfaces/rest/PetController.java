package pe.edu.upc.patitasolidaria.backend.pets.interfaces.rest;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import pe.edu.upc.patitasolidaria.backend.iam.domain.model.aggregates.JwtUserDetails;
import pe.edu.upc.patitasolidaria.backend.pets.domain.model.commands.DeletePetCommand;
import pe.edu.upc.patitasolidaria.backend.pets.domain.model.queries.GetAllPetsQuery;
import pe.edu.upc.patitasolidaria.backend.pets.domain.model.queries.GetPetByIdQuery;
import pe.edu.upc.patitasolidaria.backend.pets.domain.services.PetCommandService;
import pe.edu.upc.patitasolidaria.backend.pets.domain.services.PetQueryService;
import pe.edu.upc.patitasolidaria.backend.pets.interfaces.rest.resources.CreatePetResource;
import pe.edu.upc.patitasolidaria.backend.pets.interfaces.rest.resources.PetResource;
import pe.edu.upc.patitasolidaria.backend.pets.interfaces.rest.transform.CreatePetCommandFromResourceAssembler;
import pe.edu.upc.patitasolidaria.backend.pets.interfaces.rest.transform.PetResourceFromEntityAssembler;
import pe.edu.upc.patitasolidaria.backend.pets.interfaces.rest.transform.UpdatePetCommandFromResourceAssembler;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/pets", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Pets", description = "Pet Management Endpoints")
public class PetController {

    private final PetQueryService petQueryService;
    private final PetCommandService petCommandService;

    public PetController(PetQueryService petQueryService, PetCommandService petCommandService) {
        this.petQueryService = petQueryService;
        this.petCommandService = petCommandService;
    }

    @PostMapping
    public ResponseEntity<PetResource> createPet(
            @AuthenticationPrincipal JwtUserDetails userDetails,
            @Valid @RequestBody CreatePetResource resource
    ) {
        // ✅ Extraer el profileId del JWT (autenticación)
        Long profileId = userDetails.getProfileId();

        // ✅ Construir el comando con el profileId
        var createPetCommand = CreatePetCommandFromResourceAssembler.toCommandFromResource(resource, profileId);
        var petId = this.petCommandService.handle(createPetCommand);

        if (petId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var getPetByIdQuery = new GetPetByIdQuery(petId);
        var optionalPet = this.petQueryService.handle(getPetByIdQuery);

        var petResource = PetResourceFromEntityAssembler.toResourceFromEntity(optionalPet.get());
        return new ResponseEntity<>(petResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PetResource>> getAllPets() {
        var getAllPetsQuery = new GetAllPetsQuery();
        var pets = this.petQueryService.handle(getAllPetsQuery);
        var petResources = pets.stream()
                .map(PetResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(petResources);
    }

    @GetMapping("/{petId}")
    public ResponseEntity<PetResource> getPetById(@PathVariable Long petId) {
        var getPetByIdQuery = new GetPetByIdQuery(petId);
        var optionalPet = this.petQueryService.handle(getPetByIdQuery);
        if (optionalPet.isEmpty())
            return ResponseEntity.notFound().build();
        var petResource = PetResourceFromEntityAssembler.toResourceFromEntity(optionalPet.get());
        return ResponseEntity.ok(petResource);
    }

    @PutMapping("/{petId}")
    public ResponseEntity<PetResource> updatePet(@PathVariable Long petId, @RequestBody PetResource resource) {
        var updatePetCommand = UpdatePetCommandFromResourceAssembler.toCommandFromResource(petId, resource);
        var optionalPet = this.petCommandService.handle(updatePetCommand);

        if (optionalPet.isEmpty())
            return ResponseEntity.badRequest().build();

        var petResource = PetResourceFromEntityAssembler.toResourceFromEntity(optionalPet.get());
        return ResponseEntity.ok(petResource);
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<?> deletePet(@PathVariable Long petId) {
        var deletePetCommand = new DeletePetCommand(petId);
        this.petCommandService.handle(deletePetCommand);
        return ResponseEntity.noContent().build();
    }
}
