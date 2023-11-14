package dev.cardozo.bootcampPruebaTecnica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.cardozo.bootcampPruebaTecnica.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
