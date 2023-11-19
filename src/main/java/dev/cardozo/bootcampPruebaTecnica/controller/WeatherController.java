package dev.cardozo.bootcampPruebaTecnica.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.cardozo.bootcampPruebaTecnica.Exceptions.RateLimitExceededException;
import dev.cardozo.bootcampPruebaTecnica.config.RateLimitServiceConfig;
import dev.cardozo.bootcampPruebaTecnica.dto.AirPollutionDto;
import dev.cardozo.bootcampPruebaTecnica.dto.ForecastDto;
import dev.cardozo.bootcampPruebaTecnica.dto.MensajeDto;
import dev.cardozo.bootcampPruebaTecnica.dto.WeatherDto;
import dev.cardozo.bootcampPruebaTecnica.security.entities.User;
import dev.cardozo.bootcampPruebaTecnica.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

  @Autowired
  private RateLimitServiceConfig rateLimitServiceConfig;

  @Autowired
  private WeatherService weatherService;

  @Operation(summary = "Get Current Weather", description = "Retrieves the current weather information for a specified city.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Consulta exitosa", content = @Content(mediaType = "application/json", schema = @Schema(implementation = WeatherDto.class))),
      @ApiResponse(responseCode = "429", description = "Demasiadas solicitudes, superó el límite de tasa", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeDto.class))),
      @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeDto.class)))
  })
  @GetMapping("/current")
  public ResponseEntity<?> getCurrenWeather(
      @RequestParam String cityName, @AuthenticationPrincipal UserDetails userDetails) {
    try {
      User user = ((User) userDetails);
      LocalDateTime requestTimestamp = LocalDateTime.now();
      if (rateLimitServiceConfig.rateLimitService().allowRequest()) {
        WeatherDto weatherDto = weatherService.saveWeatherRequest(cityName, user, requestTimestamp);
        return ResponseEntity.ok(weatherDto);
      } else {
        throw new RateLimitExceededException("Demasiadas solicitudes, superó el límite de tasa");
      }
    } catch (RateLimitExceededException e) {
      return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(new MensajeDto(e.getMessage()));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MensajeDto("Error interno del servidor"));
    }

  }

  @Operation(summary = "Get Weather Forecast", description = "Retrieves the weather forecast for a specified city.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Consulta exitosa", content = @Content(mediaType = "application/json", schema = @Schema(implementation = WeatherDto.class))),
      @ApiResponse(responseCode = "429", description = "Demasiadas solicitudes, superó el límite de tasa", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeDto.class))),
      @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeDto.class)))
  })
  @GetMapping("/forecast")
  public ResponseEntity<?> getWeatherForecast(
      @RequestParam String cityName,
      @AuthenticationPrincipal UserDetails userDetails) {
    try {
      User user = ((User) userDetails);
      LocalDateTime requestTimestamp = LocalDateTime.now();
      if (rateLimitServiceConfig.rateLimitService().allowRequest()) {
        ForecastDto forecastDto = weatherService.getWeatherForecastDto(cityName);
        return ResponseEntity.ok(forecastDto);
      } else {
        throw new RateLimitExceededException("Demasiadas solicitudes, superó el límite de tasa");
      }
    } catch (RateLimitExceededException e) {
      return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(new MensajeDto(e.getMessage()));
    } catch (Exception e) {
      System.out.println(e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MensajeDto("Error interno del servidor"));
    }
  }

  @Operation(summary = "Get Air Pollution Data", description = "Retrieves air pollution data for a specified location.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Consulta exitosa", content = @Content(mediaType = "application/json", schema = @Schema(implementation = WeatherDto.class))),
      @ApiResponse(responseCode = "429", description = "Demasiadas solicitudes, superó el límite de tasa", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeDto.class))),
      @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeDto.class)))
  })
  @GetMapping("/air-pollution")
  public ResponseEntity<?> getAirPollutionData(
      @RequestParam double latitude,
      @RequestParam double longitude,
      @AuthenticationPrincipal UserDetails userDetails) {
    try {
      User user = ((User) userDetails);
      LocalDateTime requestTimestamp = LocalDateTime.now();
      if (rateLimitServiceConfig.rateLimitService().allowRequest()) {
        AirPollutionDto airPollutionDto = weatherService.getAirPollutionData(latitude, longitude);
        return ResponseEntity.ok(airPollutionDto);
      } else {
        throw new RateLimitExceededException("Demasiadas solicitudes, superó el límite de tasa");
      }
    } catch (RateLimitExceededException e) {
      return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(new MensajeDto(e.getMessage()));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MensajeDto("Error interno del servidor"));
    }
  }
}
