package pe.edu.upc.patitasolidaria.backend.adoptions.application.internal.service;

import org.springframework.stereotype.Service;
import pe.edu.upc.patitasolidaria.backend.adoptions.domain.model.entity.Adoption;
import pe.edu.upc.patitasolidaria.backend.adoptions.domain.model.valueobject.VaccinationRecord;
import pe.edu.upc.patitasolidaria.backend.adoptions.domain.model.filter.AdoptionFilter;
import pe.edu.upc.patitasolidaria.backend.adoptions.infrastructure.persistence.repository.AdoptionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdoptionServiceImpl implements AdoptionService {

    private final AdoptionRepository repository;

    public AdoptionServiceImpl(AdoptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Adoption> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Adoption> getByFilter(AdoptionFilter filter) {
        return repository.findAll().stream().filter(pet -> {
            if (filter.getGender() != null && !filter.getGender().equalsIgnoreCase(pet.getGender())) return false;
            if (filter.getSize() != null && !filter.getSize().equalsIgnoreCase(pet.getSize())) return false;
            if (filter.getActivity() != null && !filter.getActivity().equalsIgnoreCase(pet.getActivity())) return false;
            if (filter.getHair() != null && !filter.getHair().equalsIgnoreCase(pet.getHair())) return false;
            if (filter.getWeight() != null && !filter.getWeight().equalsIgnoreCase(pet.getWeight())) return false;
            return true;
        }).collect(Collectors.toList());
    }

    @Override
    public Adoption save(Adoption adoption) {
        return repository.save(adoption);
    }

    @Override
    public Adoption getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Adoption> optional = repository.findById(id);
        if (optional.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Adoption update(Long id, Adoption updatedAdoption) {
        if (!repository.existsById(id)) {
            return null;
        }
        updatedAdoption.setId(id);
        return repository.save(updatedAdoption);
    }

    @Override
    public List<Adoption> getByVaccine(String vaccine) {
        return repository.findByVaccinationRecordVaccinesContainingIgnoreCase(vaccine);
    }

    @Override
    public Adoption updateVaccinationRecord(Long adoptionId, VaccinationRecord updatedRecord) {
        Adoption adoption = repository.findById(adoptionId)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + adoptionId));

        VaccinationRecord currentRecord = adoption.getVaccinationRecord();

        if (currentRecord == null) {
            adoption.setVaccinationRecord(updatedRecord);
        } else {
            currentRecord.setVaccines(updatedRecord.getVaccines());
            currentRecord.setRecordDate(updatedRecord.getRecordDate());
            currentRecord.setLastVaccinationDate(updatedRecord.getLastVaccinationDate());
            currentRecord.setObservations(updatedRecord.getObservations());
        }

        return repository.save(adoption);
    }
}