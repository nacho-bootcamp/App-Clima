package dev.cardozo.bootcampPruebaTecnica.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.cardozo.bootcampPruebaTecnica.entities.User;
import dev.cardozo.bootcampPruebaTecnica.entities.WeatherRequest;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherRequest, Long> {
  Optional<WeatherRequest> findByCityAndUser(String city, User user);

}
