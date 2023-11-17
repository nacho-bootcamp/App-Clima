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
public class AirPollutionDto implements Serializable {
  @JsonProperty("list")
  private List<AirpollutionEntry> list;

  public static class AirpollutionEntry {
    @JsonProperty("main")
    private Main main;

    @JsonProperty("components")
    private Component components;

    public Main getMain() {
      return main;
    }

    public void setMain(Main main) {
      this.main = main;
    }

    public Component getComponents() {
      return components;
    }

    public void setComponents(Component components) {
      this.components = components;
    }
  }

  public static class Main {
    @JsonProperty("aqi")
    private int airQualityIndex;

    public int getAirQualityIndex() {
      return airQualityIndex;
    }

    public void setAirQualityIndex(int airQualityIndex) {
      this.airQualityIndex = airQualityIndex;
    }
  }

  public static class Component {
    @JsonProperty("co")
    private double carbonMonoxide;

    @JsonProperty("no")
    private double nitrogenMonoxide;

    @JsonProperty("no2")
    private double nitrogenDioxide;

    @JsonProperty("o3")
    private double ozone;

    @JsonProperty("so2")
    private double sulfurDioxide;

    @JsonProperty("pm2_5")
    private double particulateMatter2_5;

    @JsonProperty("pm10")
    private double particulateMatter10;

    @JsonProperty("nh3")
    private double ammonia;

    public double getCarbonMonoxide() {
      return carbonMonoxide;
    }

    public double getNitrogenMonoxide() {
      return nitrogenMonoxide;
    }

    public double getNitrogenDioxide() {
      return nitrogenDioxide;
    }

    public double getOzone() {
      return ozone;
    }

    public double getSulfurDioxide() {
      return sulfurDioxide;
    }

    public double getParticulateMatter2_5() {
      return particulateMatter2_5;
    }

    public double getParticulateMatter10() {
      return particulateMatter10;
    }

    public double getAmmonia() {
      return ammonia;
    }
  }
}
