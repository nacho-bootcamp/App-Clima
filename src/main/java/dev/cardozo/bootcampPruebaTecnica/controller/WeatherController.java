package dev.cardozo.bootcampPruebaTecnica.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.cardozo.bootcampPruebaTecnica.dto.AirPollutionDto;
import dev.cardozo.bootcampPruebaTecnica.dto.ForecastDto;
import dev.cardozo.bootcampPruebaTecnica.dto.WeatherDto;
import dev.cardozo.bootcampPruebaTecnica.entities.User;
import dev.cardozo.bootcampPruebaTecnica.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

  @Autowired
  private WeatherService weatherService;

  @Operation(summary = "Get Current Weather", description = "Retrieves the current weather information for a specified city.")
  @GetMapping("/current")
  public ResponseEntity<WeatherDto> getCurrenWeather(
      @RequestParam String cityName, @AuthenticationPrincipal UserDetails userDetails) {
    User user = ((User) userDetails);
    LocalDateTime requestTimestamp = LocalDateTime.now();
    WeatherDto weatherDto = weatherService.saveWeatherRequest(cityName, user, requestTimestamp);
    return ResponseEntity.ok(weatherDto);
  }

  @Operation(summary = "Get Weather Forecast", description = "Retrieves the weather forecast for a specified city.")
  @GetMapping("/forecast")
  public ResponseEntity<ForecastDto> getWeatherForecast(
      @RequestParam String cityName,
      @AuthenticationPrincipal UserDetails userDetails) {
    User user = ((User) userDetails);
    System.out.println(user.getEmail());
    LocalDateTime requestTimestamp = LocalDateTime.now();
    System.out.println(requestTimestamp);
    ForecastDto forecastDto = weatherService.getWeatherForecastDto(cityName);

    return ResponseEntity.ok(forecastDto);
  }

  @Operation(summary = "Get Air Pollution Data", description = "Retrieves air pollution data for a specified location.")
  @GetMapping("/air-pollution")
  public ResponseEntity<AirPollutionDto> getAirPollutionData(
      @RequestParam double latitude,
      @RequestParam double longitude,
      @AuthenticationPrincipal UserDetails userDetails) {
    User user = ((User) userDetails);
    System.out.println(user);
    LocalDateTime requestTimestamp = LocalDateTime.now();
    System.out.println(requestTimestamp);
    AirPollutionDto airPollutionDto = weatherService.getAirPollutionData(latitude, longitude);
    return ResponseEntity.ok(airPollutionDto);
  }
}
