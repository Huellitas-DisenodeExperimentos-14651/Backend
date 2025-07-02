package pe.edu.upc.patitasolidaria.backend.pets.domain.model.commands;

import pe.edu.upc.patitasolidaria.backend.pets.domain.model.valueobjects.PetSize;
import pe.edu.upc.patitasolidaria.backend.pets.domain.model.valueobjects.PetStatus;

public record CreatePetCommand(
        String name,
        Integer age,
        String photo,
        String breed,
        PetSize size,
        PetStatus status,
        String description,
        String healthStatus,
        String vaccinationStatus,
        String specialNeeds,
        Long profileId // 👈 nuevo campo
){
    public CreatePetCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (age != null && age < 0) {
            throw new IllegalArgumentException("Age must be non-negative");
        }
        if (breed == null || breed.isBlank()) {
            throw new IllegalArgumentException("Breed cannot be null or blank");
        }
        if (size == null) {
            throw new IllegalArgumentException("Size is required");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status is required");
        }
        if (healthStatus == null || healthStatus.isBlank()) {
            throw new IllegalArgumentException("Health status is required");
        }
        if (vaccinationStatus == null || vaccinationStatus.isBlank()) {
            throw new IllegalArgumentException("Vaccination status is required");
        }
        if (profileId == null) {
            throw new IllegalArgumentException("Profile ID is required");
        }
        if (status != PetStatus.AVAILABLE) {
            throw new IllegalArgumentException("New pets must have status AVAILABLE");
        }
        if (photo != null && photo.length() > 300) {
            throw new IllegalArgumentException("Photo URL too long");
        }
        if (specialNeeds != null && specialNeeds.length() > 300) {
            throw new IllegalArgumentException("Special needs description too long");
        }
    }
}