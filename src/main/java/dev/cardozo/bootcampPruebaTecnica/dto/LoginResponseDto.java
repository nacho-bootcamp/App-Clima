package dev.cardozo.bootcampPruebaTecnica.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
  private String token;
  private long expiresIn;
}
