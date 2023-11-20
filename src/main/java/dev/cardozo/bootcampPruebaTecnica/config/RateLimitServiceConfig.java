package dev.cardozo.bootcampPruebaTecnica.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;

@Configuration
public class RateLimitServiceConfig {

  private static final int MAX_REQUESTS_PER_HOUR = 50;

  @Bean
  public Bucket rateLimitBucket() {
    Bandwidth limit = Bandwidth.classic(MAX_REQUESTS_PER_HOUR,
        Refill.intervally(MAX_REQUESTS_PER_HOUR, Duration.ofHours(1)));
    return Bucket4j.builder().addLimit(limit).build();
  }

  @Bean
  public RateLimitService rateLimitService() {
    return new RateLimitService(rateLimitBucket());
  }
}
