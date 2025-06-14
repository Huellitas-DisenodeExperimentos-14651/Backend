package pe.edu.upc.patitasolidaria.backend.pets.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.patitasolidaria.backend.pets.domain.model.aggregates.Pet;
import pe.edu.upc.patitasolidaria.backend.pets.domain.model.queries.*;
import pe.edu.upc.patitasolidaria.backend.pets.domain.services.PetQueryService;
import pe.edu.upc.patitasolidaria.backend.pets.infrastructure.persistence.jpa.repositories.PetRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PetQueryServiceImpl implements PetQueryService {

    private final PetRepository petRepository;

    public PetQueryServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<Pet> handle(GetAllPetsQuery query) {
        return petRepository.findAll();
    }

    @Override
    public Optional<Pet> handle(GetPetByIdQuery query) {
        return petRepository.findById(query.petId());
    }

    @Override
    public List<Pet> handle(GetPetsByStatusQuery query) {
        return petRepository.findByStatus(query.status());
    }
}
