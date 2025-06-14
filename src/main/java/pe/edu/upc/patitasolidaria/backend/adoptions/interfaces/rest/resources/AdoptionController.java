package pe.edu.upc.patitasolidaria.backend.adoptions.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.patitasolidaria.backend.adoptions.application.internal.AdoptionService;
import pe.edu.upc.patitasolidaria.backend.adoptions.domain.model.Adoption;
import pe.edu.upc.patitasolidaria.backend.adoptions.domain.model.AdoptionFilter;

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

    // GET
    @GetMapping
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

    // POST
    @PostMapping
    public Adoption createAdoption(@RequestBody Adoption adoption) {
        return adoptionService.save(adoption);
    }

    // GET
    @GetMapping("/{id}")
    public Adoption getAdoptionById(@PathVariable Long id) {
        return adoptionService.getById(id);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteAdoption(@PathVariable Long id) {
        boolean removed = adoptionService.deleteById(id);
        if (removed) {
            return "Mascota eliminada con éxito (id = " + id + ")";
        } else {
            return "Mascota no encontrada (id = " + id + ")";
        }
    }

    // PUT
    @PutMapping("/{id}")
    public Adoption updateAdoption(@PathVariable Long id, @RequestBody Adoption updatedAdoption) {
        Adoption result = adoptionService.update(id, updatedAdoption);
        if (result != null) {
            return result;
        } else {
            throw new RuntimeException("Mascota no encontrada (id = " + id + ")");
        }
    }
}