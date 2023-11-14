package dev.cardozo.bootcampPruebaTecnica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.cardozo.bootcampPruebaTecnica.entities.User;
import dev.cardozo.bootcampPruebaTecnica.repositories.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping()
  public List<User> getUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/{id}")
  public User getUser(@PathVariable Long id) {
    return userRepository.findById(id).orElse(null);
  }

  @PostMapping
  public User createUser(User user) {
    return userRepository.save(user);
  }

}