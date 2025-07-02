package pe.edu.upc.patitasolidaria.backend.iam.infrastructure.tokens.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import pe.edu.upc.patitasolidaria.backend.iam.application.internal.outboundservices.tokens.TokenService;
import pe.edu.upc.patitasolidaria.backend.iam.domain.model.aggregates.JwtUserDetails;

/**
 * Interface for extracting and generating JWT bearer tokens.
 */
public interface BearerTokenService extends TokenService {

  /**
   * Extracts the JWT token from the Authorization header of a request.
   * @param request the HTTP servlet request
   * @return the bearer token or null if not found
   */
  String getBearerTokenFrom(HttpServletRequest request);

  /**
   * Generates a token from a Spring Security Authentication object.
   * @param authentication the authentication object
   * @return the JWT token
   */
  String generateToken(Authentication authentication);

  /**
   * Generates a JWT token from a custom JwtUserDetails implementation.
   * @param userDetails the JWT user details
   * @return the JWT token
   */
  String generateToken(JwtUserDetails userDetails);

  /**
   * Extracts the profileId claim from the JWT token.
   * @param token the JWT token
   * @return the profileId claim, or null if missing
   */
  Long getProfileIdFromToken(String token);
}
