package dev.cardozo.bootcampPruebaTecnica.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForecastDto {
  private List<ForecastEntry> entries;

  @Getter
  @Setter
  public static class ForecastEntry {
    private LocalDateTime timestamp;
    private String temperature;
    private String description;

  }
}
