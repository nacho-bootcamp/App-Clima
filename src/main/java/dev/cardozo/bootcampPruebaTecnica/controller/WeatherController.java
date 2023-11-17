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

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

  @Autowired
  private WeatherService weatherService;

  @GetMapping("/current")
  public ResponseEntity<WeatherDto> getCurrenWeather(
      @RequestParam String cityName, @AuthenticationPrincipal UserDetails userDetails) {
    User user = ((User) userDetails);

    // Captura la información de la solicitud
    LocalDateTime requestTimestamp = LocalDateTime.now();

    // Guarda la solicitud en la base de datos
    WeatherDto weatherDto = weatherService.saveWeatherRequest(cityName, user, requestTimestamp);

    // Devuelve la respuesta al cliente
    return ResponseEntity.ok(weatherDto);
  }

  @GetMapping("/forecast")
  public ResponseEntity<ForecastDto> getWeatherForecast(
      @RequestParam String cityName,
      @AuthenticationPrincipal UserDetails userDetails) {
    // Obtiene el usuario autenticado
    User user = ((User) userDetails);
    System.out.println(user.getEmail() + " Soy el forest");

    // Captura la información de la solicitud
    LocalDateTime requestTimestamp = LocalDateTime.now();
    System.out.println(requestTimestamp + " soy el timestamp");
    // Obtiene el pronóstico del tiempo desde el servicio
    ForecastDto forecastDto = weatherService.getWeatherForecastDto(cityName);
    System.out.println(forecastDto + " llegue ");
    // Devuelve la respuesta al cliente
    return ResponseEntity.ok(forecastDto);
  }

  @GetMapping("/air-pollution")
  public ResponseEntity<AirPollutionDto> getAirPollutionData(
      @RequestParam double latitude,
      @RequestParam double longitude,
      @AuthenticationPrincipal UserDetails userDetails) {
    // Obtiene el usuario autenticado
    User user = ((User) userDetails);
    System.out.println(user + " soy air");
    // Captura la información de la solicitud
    LocalDateTime requestTimestamp = LocalDateTime.now();
    System.out.println(requestTimestamp + " soy air de abajo");
    // Obtiene los datos de contaminación del aire desde el servicio
    AirPollutionDto airPollutionDto = weatherService.getAirPollutionData(latitude, longitude);

    // Devuelve la respuesta al cliente
    return ResponseEntity.ok(airPollutionDto);
  }
}
