package dev.cardozo.bootcampPruebaTecnica.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDto {
  private String email;
  private String password;
  private String name;
  private String lastName;
}