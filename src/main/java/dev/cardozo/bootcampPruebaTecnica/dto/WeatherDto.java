package dev.cardozo.bootcampPruebaTecnica.dto;

import java.io.Serializable;
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
public class WeatherDto implements Serializable {
  @JsonProperty("name")
  private String city;

  @JsonProperty("main")
  private Main main;

  @JsonProperty("wind")
  private Wind wind;

  @JsonProperty("weather")
  private List<WeatherDescription> weather;

  public Main getMain() {
    return main;
  }

  public Wind getWind() {
    return wind;
  }

  public List<WeatherDescription> getWeather() {
    return weather;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Main {
    @JsonProperty("temp")
    private double temperature;

    @JsonProperty("humidity")
    private int humidity;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Wind {
    @JsonProperty("speed")
    private double speed;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class WeatherDescription {
    private String description;
  }
}
