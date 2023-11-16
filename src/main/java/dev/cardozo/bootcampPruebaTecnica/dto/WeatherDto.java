package dev.cardozo.bootcampPruebaTecnica.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherDto {
  private String city;
  private String temperature;
  private String description;
  private String humidity;
  private String windSpeed;
}
