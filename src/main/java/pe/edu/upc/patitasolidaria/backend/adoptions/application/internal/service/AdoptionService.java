package pe.edu.upc.patitasolidaria.backend.adoptions.application.internal.service;

import pe.edu.upc.patitasolidaria.backend.adoptions.domain.model.entity.Adoption;
import pe.edu.upc.patitasolidaria.backend.adoptions.domain.model.filter.AdoptionFilter;
import pe.edu.upc.patitasolidaria.backend.adoptions.domain.model.valueobject.VaccinationRecord;

import java.util.List;

public interface AdoptionService {
    List<Adoption> getAll();
    List<Adoption> getByFilter(AdoptionFilter filter);
    Adoption save(Adoption adoption);
    Adoption getById(Long id);
    boolean deleteById(Long id);
    Adoption update(Long id, Adoption updatedAdoption);

    List<Adoption> getByVaccine(String vaccine);
    Adoption updateVaccinationRecord(Long adoptionId, VaccinationRecord updatedRecord);
}