package pe.edu.upc.patitasolidaria.backend.iam.infrastructure.tokens.jwt.services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pe.edu.upc.patitasolidaria.backend.iam.application.internal.outboundservices.tokens.TokenService;
import pe.edu.upc.patitasolidaria.backend.iam.domain.model.aggregates.JwtUserDetails;
import pe.edu.upc.patitasolidaria.backend.iam.infrastructure.tokens.jwt.BearerTokenService;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
public class TokenServiceImpl implements BearerTokenService {
  private final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);

  private static final String AUTHORIZATION_PARAMETER_NAME = "Authorization";
  private static final String BEARER_TOKEN_PREFIX = "Bearer ";
  private static final int TOKEN_BEGIN_INDEX = 7;

  @Value("${authorization.jwt.secret}")
  private String secret;

  @Value("${authorization.jwt.expiration.days}")
  private int expirationDays;

  // ✅ NUEVO MÉTODO que usa JwtUserDetails directamente
  public String generateToken(JwtUserDetails userDetails) {
    return buildTokenWithDefaultParameters(userDetails.getUsername(), userDetails.getProfileId());
  }

  // ✅ IMPLEMENTACIÓN OBLIGATORIA DEL MÉTODO DE LA INTERFAZ
  @Override
  public String generateToken(Authentication authentication) {
    if (!(authentication.getPrincipal() instanceof JwtUserDetails userDetails)) {
      throw new IllegalArgumentException("Expected JwtUserDetails as principal");
    }
    return generateToken(userDetails);
  }

  // ✅ IMPLEMENTACIÓN PARA CUANDO SE QUIERA GENERAR TOKEN SOLO CON USERNAME
  @Override
  public String generateToken(String username) {
    throw new UnsupportedOperationException("Use generateToken(JwtUserDetails) instead, to include profileId");
  }

  // ✅ GENERACIÓN DEL JWT CON profileId COMO CLAIM
  private String buildTokenWithDefaultParameters(String username, Long profileId) {
    var issuedAt = new Date();
    var expiration = DateUtils.addDays(issuedAt, expirationDays);
    var key = getSigningKey();

    return Jwts.builder()
            .subject(username)
            .claim("profileId", profileId)
            .issuedAt(issuedAt)
            .expiration(expiration)
            .signWith(key)
            .compact();
  }

  @Override
  public String getUsernameFromToken(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  @Override
  public boolean validateToken(String token) {
    try {
      Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);
      LOGGER.info("Token is valid");
      return true;
    } catch (SignatureException e) {
      LOGGER.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      LOGGER.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      LOGGER.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      LOGGER.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      LOGGER.error("JWT claims string is empty: {}", e.getMessage());
    }
    return false;
  }

  @Override
  public Long getProfileIdFromToken(String token) {
    Claims claims = extractAllClaims(token);
    return claims.get("profileId", Long.class);
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
  }

  private SecretKey getSigningKey() {
    byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  @Override
  public String getBearerTokenFrom(HttpServletRequest request) {
    String header = request.getHeader(AUTHORIZATION_PARAMETER_NAME);
    if (StringUtils.hasText(header) && header.startsWith(BEARER_TOKEN_PREFIX)) {
      return header.substring(TOKEN_BEGIN_INDEX);
    }
    return null;
  }
}
