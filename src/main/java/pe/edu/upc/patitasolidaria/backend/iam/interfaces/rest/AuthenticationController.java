package pe.edu.upc.patitasolidaria.backend.iam.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.patitasolidaria.backend.iam.domain.model.aggregates.JwtUserDetails;
import pe.edu.upc.patitasolidaria.backend.iam.domain.services.UserCommandService;
import pe.edu.upc.patitasolidaria.backend.iam.infrastructure.tokens.jwt.BearerTokenService;
import pe.edu.upc.patitasolidaria.backend.iam.interfaces.rest.resources.AuthenticatedUserResource;
import pe.edu.upc.patitasolidaria.backend.iam.interfaces.rest.resources.SignInResource;
import pe.edu.upc.patitasolidaria.backend.iam.interfaces.rest.resources.SignUpResource;
import pe.edu.upc.patitasolidaria.backend.iam.interfaces.rest.resources.UserResource;
import pe.edu.upc.patitasolidaria.backend.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import pe.edu.upc.patitasolidaria.backend.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import pe.edu.upc.patitasolidaria.backend.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import pe.edu.upc.patitasolidaria.backend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;

@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Endpoints")
public class AuthenticationController {

  private final UserCommandService userCommandService;
  private final BearerTokenService tokenService;

  public AuthenticationController(UserCommandService userCommandService,
                                  BearerTokenService tokenService) {
    this.userCommandService = userCommandService;
    this.tokenService = tokenService;
  }

  @Operation(summary = "Sign in", description = "Authenticates a user and returns JWT + profile data.")
  @PostMapping("/sign-in")
  public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody SignInResource signInResource) {
    var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);
    var authenticatedUser = userCommandService.handle(signInCommand);

    if (authenticatedUser.isEmpty()) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    var user = authenticatedUser.get().getLeft();
    var token = authenticatedUser.get().getRight();
    var profile = user.getProfile();

    var resource = AuthenticatedUserResourceFromEntityAssembler
            .toResourceFromEntity(user, profile, token);

    return ResponseEntity.ok(resource);
  }

  @Operation(
          summary = "Sign up",
          description = "Creates a new user and an associated profile in one request."
  )
  @ApiResponse(responseCode = "201", description = "User created successfully")
  @ApiResponse(responseCode = "400", description = "Invalid input or user already exists")
  @PostMapping("/sign-up")
  public ResponseEntity<UserResource> signUp(@RequestBody SignUpResource signUpResource) {
    var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
    var user = userCommandService.handle(signUpCommand);

    if (user.isEmpty()) {
      return ResponseEntity.badRequest().build();
    }

    var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
    return new ResponseEntity<>(userResource, HttpStatus.CREATED);
  }
}
