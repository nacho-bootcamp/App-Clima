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
public class RegisterUserDto implements Serializable {
  private String email;
  private String password;
  private String name;
  private String lastName;
}
