package pe.edu.upc.patitasolidaria.backend.adoptions.application.internal;

import pe.edu.upc.patitasolidaria.backend.adoptions.domain.model.Adoption;
import pe.edu.upc.patitasolidaria.backend.adoptions.domain.model.AdoptionFilter;

import java.util.List;

public interface AdoptionService {
    List<Adoption> getAll();
    List<Adoption> getByFilter(AdoptionFilter filter);
    Adoption save(Adoption adoption);
    Adoption getById(Long id);
    boolean deleteById(Long id);
    Adoption update(Long id, Adoption updatedAdoption); // hijo de 🐩
}