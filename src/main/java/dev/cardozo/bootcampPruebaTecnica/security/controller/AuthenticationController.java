package dev.cardozo.bootcampPruebaTecnica.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.cardozo.bootcampPruebaTecnica.dto.MensajeDto;
import dev.cardozo.bootcampPruebaTecnica.security.dto.LoginResponseDto;
import dev.cardozo.bootcampPruebaTecnica.security.dto.LoginUserDto;
import dev.cardozo.bootcampPruebaTecnica.security.dto.RegisterUserDto;
import dev.cardozo.bootcampPruebaTecnica.security.entities.User;
import dev.cardozo.bootcampPruebaTecnica.security.service.AuthenticationService;
import dev.cardozo.bootcampPruebaTecnica.security.service.JwtService;
import dev.cardozo.bootcampPruebaTecnica.security.service.UserService;
import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationService authenticationService;
  @Autowired
  private JwtService jwtService;
  @Autowired
  private UserService userService;

  @Operation(summary = "Register a new User", description = "This operation allows registering a new user in the system")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Registro de usuario exitoso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
      @ApiResponse(responseCode = "400", description = "Solicitud incorrecta o datos de registro inválidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeDto.class))),
      @ApiResponse(responseCode = "409", description = "Conflicto, el email ya está registrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeDto.class))),
      @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeDto.class)))
  })
  @PostMapping("/signup")
  public ResponseEntity<?> register(@RequestBody RegisterUserDto registerUserDto, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return new ResponseEntity<MensajeDto>(new MensajeDto("Verifique los datos introducidos"), HttpStatus.BAD_REQUEST);
    }
    if (userService.existsByEmail(registerUserDto.getEmail())) {
      return new ResponseEntity<MensajeDto>(
          new MensajeDto("Esta cuenta ya se encuentra registrada"),
          HttpStatus.BAD_REQUEST);
    }
    if (registerUserDto.getPassword().length() < 4) {
      return new ResponseEntity<MensajeDto>(new MensajeDto("La contraseña debe tener al menos 6 caracteres"),
          HttpStatus.BAD_REQUEST);
    }
    if (StringUtils.isEmpty(registerUserDto.getName()) || StringUtils.isEmpty(registerUserDto.getLastName())) {
      return new ResponseEntity<MensajeDto>(new MensajeDto("Nombre y apellido son campos obligatorios"),
          HttpStatus.BAD_REQUEST);
    }

    User registeredUser = authenticationService.signup(registerUserDto);
    return ResponseEntity.ok(registeredUser);
  }

  @Operation(summary = "Authenticate user and generate JWT token", description = "Authenticates an existing user in the system and generates a JWT token for subsequent authentication")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Autenticación exitosa", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Solicitud incorrecta o datos de autenticación inválidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeDto.class))),
      @ApiResponse(responseCode = "401", description = "Credenciales de usuario no válidas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeDto.class))),
      @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeDto.class))),
      @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeDto.class)))
  })
  @PostMapping("/signin")
  public ResponseEntity<?> authenticate(@RequestBody LoginUserDto loginUserDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<MensajeDto>(new MensajeDto("Verifique los datos introducidos"), HttpStatus.BAD_REQUEST);
    }
    try {
      User authenticatedUser = authenticationService.signin(loginUserDto);
      String jwtToken = jwtService.generateToken(authenticatedUser);
      LoginResponseDto loginResponse = new LoginResponseDto();
      loginResponse.setToken(jwtToken);
      loginResponse.setExpiresIn(jwtService.getExpirationTime());

      return ResponseEntity.ok(loginResponse);

    } catch (UsernameNotFoundException e) {
      return new ResponseEntity<MensajeDto>(new MensajeDto("Usuario no encontrado"), HttpStatus.BAD_REQUEST);
    } catch (BadCredentialsException e) {
      return new ResponseEntity<MensajeDto>(new MensajeDto("Credenciales inválidas"), HttpStatus.BAD_REQUEST);
    } catch (RuntimeException e) {
      return new ResponseEntity<MensajeDto>(new MensajeDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
  }
}
