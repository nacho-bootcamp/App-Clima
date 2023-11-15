package dev.cardozo.bootcampPruebaTecnica.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.cardozo.bootcampPruebaTecnica.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
}
