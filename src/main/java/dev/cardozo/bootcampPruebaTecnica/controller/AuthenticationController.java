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

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationService authenticationService;
  @Autowired
  private JwtService jwtService;

  @PostMapping("/signup")
  public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
    User registeredUser = authenticationService.signup(registerUserDto);

    return ResponseEntity.ok(registeredUser);
  }

  @PostMapping("/signin")
  public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginUserDto loginUserDto) {

    User authenticatedUser = authenticationService.signin(loginUserDto);
    System.out.println("Usuario autenticado" + authenticatedUser);

    String jwtToken = jwtService.generateToken(authenticatedUser);

    LoginResponseDto loginResponse = new LoginResponseDto();
    loginResponse.setToken(jwtToken);
    loginResponse.setExpiresIn(jwtService.getExpirationTime());

    System.out.println("Token Generado" + jwtToken);
    return ResponseEntity.ok(loginResponse);
  }
}
