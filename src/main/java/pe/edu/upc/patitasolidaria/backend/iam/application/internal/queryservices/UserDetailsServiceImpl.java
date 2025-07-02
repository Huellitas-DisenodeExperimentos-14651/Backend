package pe.edu.upc.patitasolidaria.backend.iam.application.internal.queryservices;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.upc.patitasolidaria.backend.iam.domain.model.aggregates.User;
import pe.edu.upc.patitasolidaria.backend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import pe.edu.upc.patitasolidaria.backend.iam.domain.model.aggregates.JwtUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Carga el usuario desde la base de datos y construye JwtUserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        if (user.getProfile() == null) {
            throw new IllegalStateException("El usuario no tiene un perfil asociado");
        }

        return new JwtUserDetails(
                user.getUsername(),
                user.getPassword(),
                user.getProfile().getId(),
                user.getProfile().getRole().name() // ← "ADOPTER", "SHELTER", etc.
        );
    }
}
