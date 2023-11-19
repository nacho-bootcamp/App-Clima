package dev.cardozo.bootcampPruebaTecnica.Exceptions;

public class RateLimitExceededException extends RuntimeException {
  public RateLimitExceededException(String message) {
    super(message);
  }
}
