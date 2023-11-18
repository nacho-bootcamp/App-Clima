package dev.cardozo.bootcampPruebaTecnica.config;

import io.github.bucket4j.Bucket;

public class RateLimitService {

  private final Bucket bucket;

  public RateLimitService(Bucket bucket) {
    this.bucket = bucket;
  }

  public boolean allowRequest() {
    return bucket.tryConsume(1);
  }

}
