package pe.edu.upc.patitasolidaria.backend.adoptions.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.patitasolidaria.backend.adoptions.application.internal.service.AdoptionService;
import pe.edu.upc.patitasolidaria.backend.adoptions.domain.model.entity.Adoption;
import pe.edu.upc.patitasolidaria.backend.adoptions.domain.model.valueobject.VaccinationRecord;
import pe.edu.upc.patitasolidaria.backend.adoptions.domain.model.filter.AdoptionFilter;

import java.util.List;

@RestController
@RequestMapping("/api/adoptions")
@CrossOrigin(origins = "*")
@Tag(name = "Adoptions", description = "Adoptions Management Endpoints")
public class AdoptionController {

    private final AdoptionService adoptionService;

    @Autowired
    public AdoptionController(AdoptionService adoptionService) {
        this.adoptionService = adoptionService;
    }

    @GetMapping
    @Operation(summary = "List all adoptions or filter by attributes")
    public List<Adoption> getAllAdoptions(
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) String activity,
            @RequestParam(required = false) String hair,
            @RequestParam(required = false) String weight
    ) {
        if (gender == null && size == null && activity == null && hair == null && weight == null) {
            return adoptionService.getAll();
        }

        AdoptionFilter filter = new AdoptionFilter();
        filter.setGender(gender);
        filter.setSize(size);
        filter.setActivity(activity);
        filter.setHair(hair);
        filter.setWeight(weight);
        return adoptionService.getByFilter(filter);
    }

    @PostMapping
    @Operation(summary = "Create a new adoption")
    public Adoption createAdoption(@RequestBody Adoption adoption) {
        return adoptionService.save(adoption);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get adoption by ID")
    public Adoption getAdoptionById(@PathVariable Long id) {
        return adoptionService.getById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete adoption by ID")
    public String deleteAdoption(@PathVariable Long id) {
        boolean removed = adoptionService.deleteById(id);
        if (removed) {
            return "Mascota eliminada con éxito (id = " + id + ")";
        } else {
            return "Mascota no encontrada (id = " + id + ")";
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update adoption by ID")
    public Adoption updateAdoption(@PathVariable Long id, @RequestBody Adoption updatedAdoption) {
        Adoption result = adoptionService.update(id, updatedAdoption);
        if (result != null) {
            return result;
        } else {
            throw new RuntimeException("Mascota no encontrada (id = " + id + ")");
        }
    }

    @GetMapping("/by-vaccine")
    @Operation(summary = "Get adoptions by vaccine name")
    public List<Adoption> getAdoptionsByVaccine(@RequestParam String vaccine) {
        return adoptionService.getByVaccine(vaccine);
    }

    // NUEVO: Actualizar vacunas solamente
    @PutMapping("/{id}/vaccines")
    @Operation(summary = "Update vaccination record of an adoption")
    public Adoption updateVaccinationRecord(
            @PathVariable Long id,
            @RequestBody VaccinationRecord updatedRecord
    ) {
        return adoptionService.updateVaccinationRecord(id, updatedRecord);
    }
}