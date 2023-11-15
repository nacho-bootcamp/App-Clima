package dev.cardozo.bootcampPruebaTecnica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.cardozo.bootcampPruebaTecnica.entities.User;

import dev.cardozo.bootcampPruebaTecnica.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/user")
  public ResponseEntity<User> authenticatedUser() {
    User currentUser = userService.authenticatedUser();
    return ResponseEntity.ok(currentUser);
  }

  @GetMapping("/users")
  public ResponseEntity<List<User>> allUsers() {
    List<User> users = userService.allUsers();
    return ResponseEntity.ok(users);
  }
}
