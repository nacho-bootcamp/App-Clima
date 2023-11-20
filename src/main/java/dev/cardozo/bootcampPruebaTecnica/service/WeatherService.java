package dev.cardozo.bootcampPruebaTecnica.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dev.cardozo.bootcampPruebaTecnica.config.CacheConfig;
import dev.cardozo.bootcampPruebaTecnica.dto.AirPollutionDto;
import dev.cardozo.bootcampPruebaTecnica.dto.ForecastDto;
import dev.cardozo.bootcampPruebaTecnica.dto.WeatherDto;
import dev.cardozo.bootcampPruebaTecnica.entities.WeatherRequest;
import dev.cardozo.bootcampPruebaTecnica.repositories.WeatherRepository;
import dev.cardozo.bootcampPruebaTecnica.security.entities.User;

@Import({ CacheConfig.class })
@ImportAutoConfiguration(classes = {
    CacheAutoConfiguration.class,
    RedisAutoConfiguration.class
})
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

  @Cacheable("getWeatherData")
  public WeatherDto getWeatherData(String cityName) {
    String apiUrl = weatherApiUrl + "/weather?q=" + cityName + "&appid=" + apiKey;
    WeatherDto weatherDto = callWeatherApi(apiUrl, WeatherDto.class);
    return weatherDto;
  }

  @Cacheable("getWeatherForecastDto")
  public ForecastDto getWeatherForecastDto(String cityName) {
    try {
      String apiUrl = weatherApiUrl + "/forecast?q=" + cityName + "&appid=" + apiKey;
      ForecastDto forecastDto = callWeatherApi(apiUrl, ForecastDto.class);
      if (forecastDto != null && !forecastDto.getList().isEmpty()) {
        List<ForecastDto.WeatherData> entries = new ArrayList<>();
        Set<LocalDate> uniqueDates = new HashSet<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (ForecastDto.WeatherData weatherData : forecastDto.getList()) {
          LocalDateTime dateTime = LocalDateTime.parse(weatherData.getDtTxt(), formatter);
          LocalDate date = dateTime.toLocalDate();

          if (uniqueDates.add(date)) {
            entries.add(weatherData);
          }
        }

        forecastDto.setList(entries);
      }
      return forecastDto;
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  @Cacheable("getAirPollutionData")
  public AirPollutionDto getAirPollutionData(double latitude, double longitude) {
    try {
      String apiUrl = weatherApiUrl + "/air_pollution?lat=" + latitude + "&lon=" + longitude + "&appid=" + apiKey;
      AirPollutionDto airPollutionDto = callWeatherApi(apiUrl, AirPollutionDto.class);
      return airPollutionDto;
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  private <T> T callWeatherApi(String apiUrl, Class<T> responseType) {
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.getForObject(apiUrl, responseType);
  }
}
