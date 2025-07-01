package pe.edu.upc.patitasolidaria.backend.iam.interfaces.rest.resources;

import java.util.List;

public record SignUpResource(
        String username,
        String password,
        String roles,

        // Datos de perfil incluidos en el sign-up:
        String name,
        String email,
        String address,
        String role, // tipo de perfil (adopter, donor...)
        List<String> paymentMethods,
        List<String> preferences,
        String profilePic,
        String bio,
        Integer capacity,
        Integer animalsAvailable,
        String homeType,
        String previousExperience
) {}

