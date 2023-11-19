package dev.cardozo.bootcampPruebaTecnica.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import dev.cardozo.bootcampPruebaTecnica.security.entities.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "weather")
@Getter
@Setter
public class WeatherRequest {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String city;

  private LocalDateTime requestTimestamp;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}
