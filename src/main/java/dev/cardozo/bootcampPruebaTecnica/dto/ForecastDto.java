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
public class ForecastDto implements Serializable {
  private String cod;
  private int message;
  private int cnt;
  private List<WeatherData> list;

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class WeatherData implements Serializable {
    private long dt;
    private Main main;
    private List<Weather> weather;
    private Clouds clouds;
    private Wind wind;
    private int visibility;
    private int pop;
    private Sys sys;
    @JsonProperty("dt_txt")
    private String dtTxt;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Main implements Serializable {
      private double temp;
      private double feelsLike;
      @JsonProperty("temp_min")
      private double tempMin;
      @JsonProperty("temp_max")
      private double tempMax;
      private int pressure;
      @JsonProperty("sea_level")
      private int seaLevel;
      @JsonProperty("grnd_level")
      private int grndLevel;
      private int humidity;
      @JsonProperty("temp_kf")
      private double tempKf;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Weather implements Serializable {
      private int id;
      private String main;
      private String description;
      private String icon;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Clouds implements Serializable {
      private int all;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Wind implements Serializable {
      private double speed;
      private int deg;
      private double gust;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Sys implements Serializable {
      private String pod;
    }
  }
}
