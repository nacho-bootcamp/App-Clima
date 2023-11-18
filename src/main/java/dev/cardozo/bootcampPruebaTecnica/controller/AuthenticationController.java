package dev.cardozo.bootcampPruebaTecnica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.cardozo.bootcampPruebaTecnica.dto.LoginResponseDto;
import dev.cardozo.bootcampPruebaTecnica.dto.LoginUserDto;
import dev.cardozo.bootcampPruebaTecnica.dto.RegisterUserDto;
import dev.cardozo.bootcampPruebaTecnica.entities.User;
import dev.cardozo.bootcampPruebaTecnica.service.AuthenticationService;
import dev.cardozo.bootcampPruebaTecnica.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationService authenticationService;
  @Autowired
  private JwtService jwtService;

  @Operation(summary = "Register a new User", description = "This operation allows registering a new user in the system")
  @PostMapping("/signup")
  public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
    User registeredUser = authenticationService.signup(registerUserDto);

    return ResponseEntity.ok(registeredUser);
  }

  @Operation(summary = "Authenticate user and generate JWT token", description = "Authenticates an existing user in the system and generates a JWT token for subsequent authentication")
  @PostMapping("/signin")
  public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginUserDto loginUserDto) {

    User authenticatedUser = authenticationService.signin(loginUserDto);
    String jwtToken = jwtService.generateToken(authenticatedUser);

    LoginResponseDto loginResponse = new LoginResponseDto();
    loginResponse.setToken(jwtToken);
    loginResponse.setExpiresIn(jwtService.getExpirationTime());

    return ResponseEntity.ok(loginResponse);
  }
}
