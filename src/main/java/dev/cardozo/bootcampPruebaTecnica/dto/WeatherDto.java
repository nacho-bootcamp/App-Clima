package dev.cardozo.bootcampPruebaTecnica.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherDto {
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
  public static class Main {
    @JsonProperty("temp")
    private double temperature;

    @JsonProperty("humidity")
    private int humidity;
  }

  @Getter
  @Setter
  public static class Wind {
    @JsonProperty("speed")
    private double speed;
  }

  @Getter
  @Setter
  public static class WeatherDescription {
    private String description;
  }
}
