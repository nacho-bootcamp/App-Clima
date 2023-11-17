package dev.cardozo.bootcampPruebaTecnica.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto implements Serializable {
  private String token;
  private long expiresIn;
}
