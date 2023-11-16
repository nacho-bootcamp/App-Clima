package dev.cardozo.bootcampPruebaTecnica.dto;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirPollutionDto {
  private int airQualityIndex;
  private String mainPollutant;
  private Map<String, Double> pollutants;
}
