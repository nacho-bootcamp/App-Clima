package dev.cardozo.bootcampPruebaTecnica.Exceptions;

public class UnauthorizedAccessException extends RuntimeException {
  public UnauthorizedAccessException(String message) {
    super(message);
  }
}
