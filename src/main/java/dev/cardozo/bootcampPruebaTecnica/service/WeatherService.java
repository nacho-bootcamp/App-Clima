package dev.cardozo.bootcampPruebaTecnica.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dev.cardozo.bootcampPruebaTecnica.dto.AirPollutionDto;
import dev.cardozo.bootcampPruebaTecnica.dto.ForecastDto;
import dev.cardozo.bootcampPruebaTecnica.dto.WeatherDto;
import dev.cardozo.bootcampPruebaTecnica.entities.User;
import dev.cardozo.bootcampPruebaTecnica.entities.WeatherRequest;
import dev.cardozo.bootcampPruebaTecnica.repositories.WeatherRepository;

@Service
public class WeatherService {

  @Autowired
  private WeatherRepository weatherRepository;

  @Value("${weather.api.url}")
  private String weatherApiUrl;

  @Value("${weather.api.key}")
  private String apiKey;

  public WeatherDto saveWeatherRequest(String cityName, User user, LocalDateTime requestTimestamp) {
    WeatherRequest weatherRequest = new WeatherRequest();
    weatherRequest.setCity(cityName);
    weatherRequest.setRequestTimestamp(requestTimestamp);
    weatherRequest.setUser(user);
    weatherRepository.save(weatherRequest);

    return getWeatherData(cityName);
  }

  public WeatherDto getWeatherData(String cityName) {
    String apiUrl = weatherApiUrl + "/weather?q=" + cityName + "&appid=" + apiKey;
    WeatherDto weatherDto = callWeatherApi(apiUrl, WeatherDto.class);
    return weatherDto;
  }

  public ForecastDto getWeatherForecastDto(String cityName) {
    String apiUrl = weatherApiUrl + "/forecast?q=" + cityName + "&appid=" + apiKey;
    System.out.println(apiUrl);
    ForecastDto forecastDto = callWeatherApi(apiUrl, ForecastDto.class);
    System.out.println(forecastDto);
    return forecastDto;
  }

  public AirPollutionDto getAirPollutionData(String cityName) {
    System.out.println("estoy en Air");
    String apiUrl = weatherApiUrl + "/air_pollution?q=" + cityName + "&appid=" + apiKey;
    System.out.println(apiUrl);
    AirPollutionDto airPollutionDto = callWeatherApi(apiUrl, AirPollutionDto.class);
    return airPollutionDto;
  }

  private <T> T callWeatherApi(String apiUrl, Class<T> responseType) {
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.getForObject(apiUrl, responseType);
  }
}
// https://api.openweathermap.org/data/2.5/forescast?q=London&appid=5baa0baa0ba4f34fb33e08b011630046
// https://api.openweathermap.org/data/2.5/forecast?q=London&appid=5baa0baa0ba4f34fb33e08b011630046