package dev.cardozo.bootcampPruebaTecnica.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import dev.cardozo.bootcampPruebaTecnica.security.entities.User;
import dev.cardozo.bootcampPruebaTecnica.security.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User authenticatedUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return (User) authentication.getPrincipal();
  }

  public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  public List<User> allUsers() {
    List<User> users = new ArrayList<>();
    userRepository.findAll().forEach(users::add);
    return users;
  }
}
