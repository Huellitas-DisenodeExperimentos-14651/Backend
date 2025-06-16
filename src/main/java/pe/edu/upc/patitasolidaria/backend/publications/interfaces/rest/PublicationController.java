package pe.edu.upc.patitasolidaria.backend.publications.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import pe.edu.upc.patitasolidaria.backend.publications.domain.model.commands.DeletePublicationCommand;
import pe.edu.upc.patitasolidaria.backend.publications.domain.model.queries.*;
import pe.edu.upc.patitasolidaria.backend.publications.domain.services.PublicationCommandService;
import pe.edu.upc.patitasolidaria.backend.publications.domain.services.PublicationQueryService;
import pe.edu.upc.patitasolidaria.backend.publications.interfaces.rest.resources.CreatePublicationResource;
import pe.edu.upc.patitasolidaria.backend.publications.interfaces.rest.resources.PublicationResource;
import pe.edu.upc.patitasolidaria.backend.publications.interfaces.rest.transform.CreatePublicationCommandFromResourceAssembler;
import pe.edu.upc.patitasolidaria.backend.publications.interfaces.rest.transform.PublicationResourceFromEntityAssembler;
import pe.edu.upc.patitasolidaria.backend.publications.interfaces.rest.transform.UpdatePublicationCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping(value = "/api/v1/publications", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Publications", description = "Publication Management Endpoints")
public class PublicationController {

    private final PublicationQueryService publicationQueryService;
    private final PublicationCommandService publicationCommandService;

    public PublicationController(PublicationQueryService publicationQueryService, PublicationCommandService publicationCommandService) {
        this.publicationQueryService = publicationQueryService;
        this.publicationCommandService = publicationCommandService;
    }

    @PostMapping
    public ResponseEntity<PublicationResource> createPublication(@RequestBody CreatePublicationResource resource) {
        var createCommand = CreatePublicationCommandFromResourceAssembler.toCommandFromResource(resource);
        var publicationId = this.publicationCommandService.handle(createCommand);

        if (publicationId == 0L) return ResponseEntity.badRequest().build();

        var query = new GetPublicationByIdQuery(publicationId);
        var optionalPublication = this.publicationQueryService.handle(query);

        var publicationResource = PublicationResourceFromEntityAssembler.toResourceFromEntity(optionalPublication.get());
        return new ResponseEntity<>(publicationResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PublicationResource>> getAllPublications() {
        var query = new GetAllPublicationsQuery();
        var publications = this.publicationQueryService.handle(query);
        var resources = publications.stream()
                .map(PublicationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{publicationId}")
    public ResponseEntity<PublicationResource> getPublicationById(@PathVariable Long publicationId) {
        var query = new GetPublicationByIdQuery(publicationId);
        var optionalPublication = this.publicationQueryService.handle(query);

        return optionalPublication
                .map(publication -> ResponseEntity.ok(PublicationResourceFromEntityAssembler.toResourceFromEntity(publication)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<PublicationResource>> getPublicationsByPetId(@PathVariable Long petId) {
        var query = new GetPublicationsByPetIdQuery(petId);
        var publications = this.publicationQueryService.handle(query);
        var resources = publications.stream()
                .map(PublicationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{publicationId}")
    public ResponseEntity<PublicationResource> updatePublication(@PathVariable Long publicationId, @RequestBody PublicationResource resource) {
        var command = UpdatePublicationCommandFromResourceAssembler.toCommandFromResource(publicationId, resource);
        var optionalUpdated = this.publicationCommandService.handle(command);

        return optionalUpdated
                .map(publication -> ResponseEntity.ok(PublicationResourceFromEntityAssembler.toResourceFromEntity(publication)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{publicationId}")
    public ResponseEntity<?> deletePublication(@PathVariable Long publicationId) {
        var command = new DeletePublicationCommand(publicationId);
        this.publicationCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}
