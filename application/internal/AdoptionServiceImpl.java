package pe.edu.upc.patitasolidaria.backend.adoptions.application.internal;

import org.springframework.stereotype.Service;
import pe.edu.upc.patitasolidaria.backend.adoptions.domain.model.Adoption;
import pe.edu.upc.patitasolidaria.backend.adoptions.domain.model.AdoptionFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdoptionServiceImpl implements AdoptionService {

    private final List<Adoption> mockAdoptions = new ArrayList<>();

    @Override
    public List<Adoption> getAll() {
        return mockAdoptions;
    }

    @Override
    public List<Adoption> getByFilter(AdoptionFilter filter) {
        return mockAdoptions.stream().filter(pet -> {
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
        Long newId = (long) (mockAdoptions.size() + 1);
        adoption.setId(newId);
        mockAdoptions.add(adoption);
        return adoption;
    }

    @Override
    public Adoption getById(Long id) {
        return mockAdoptions.stream()
                .filter(pet -> pet.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        return mockAdoptions.removeIf(pet -> pet.getId().equals(id));
    }

    @Override
    public Adoption update(Long id, Adoption updatedAdoption) {
        for (int i = 0; i < mockAdoptions.size(); i++) {
            Adoption existing = mockAdoptions.get(i);
            if (existing.getId().equals(id)) {
                updatedAdoption.setId(id);
                mockAdoptions.set(i, updatedAdoption);
                return updatedAdoption;
            }
        }
        return null;
    }
}