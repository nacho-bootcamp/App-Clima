package dev.cardozo.bootcampPruebaTecnica.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ForecastDto {
  @JsonProperty("list")
  private List<ForecastEntry> entries;

  @Getter
  @Setter
  public static class ForecastEntry {
    @JsonProperty("dt_txt")
    private String timestamp;

    @JsonProperty("main")
    private Main main;

    @JsonProperty("weather")
    private List<Weather> weather;
  }

  @Getter
  @Setter
  public static class Main {
    @JsonProperty("temp")
    private double temperature;
  }

  @Getter
  @Setter
  public static class Weather {
    private String description;
  }
}
