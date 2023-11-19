package dev.cardozo.bootcampPruebaTecnica.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.cardozo.bootcampPruebaTecnica.security.dto.LoginUserDto;
import dev.cardozo.bootcampPruebaTecnica.security.dto.RegisterUserDto;
import dev.cardozo.bootcampPruebaTecnica.security.entities.User;
import dev.cardozo.bootcampPruebaTecnica.security.repository.UserRepository;

@Service
public class AuthenticationService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AuthenticationManager authenticationManager;

  public User signup(RegisterUserDto registerUser) {
    User user = new User();
    user.setName(registerUser.getName());
    user.setLastName(registerUser.getLastName());
    user.setEmail(registerUser.getEmail());
    user.setPassword(passwordEncoder.encode(registerUser.getPassword()));

    return userRepository.save(user);
  }

  public User signin(LoginUserDto loginUser) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              loginUser.getEmail(),
              loginUser.getPassword()));
    } catch (AuthenticationException e) {
      throw new RuntimeException("Autenticación fallida: " + e.getMessage());
    }

    return userRepository.findByEmail(loginUser.getEmail())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
  }

  public boolean checkPassword(String rawPassword, String encodedPassword) {
    return passwordEncoder.matches(rawPassword, encodedPassword);
  }
}
