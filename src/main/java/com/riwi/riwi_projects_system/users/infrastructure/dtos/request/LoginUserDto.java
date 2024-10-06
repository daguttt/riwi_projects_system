package com.riwi.riwi_projects_system.users.infrastructure.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDto {
  @Schema(example = "john@doe.com", description = "Email of the user")
  @NotBlank(message = "Email cannot be blank")
  @Pattern(
      regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
      message = "Email must be valid")
  private String email;

  @Schema(example = "JohnDoe1234*",
      description = "Password of the user with at least one uppercase letter, one lowercase letter, one number, and one special character")
  @NotBlank(message = "Password cannot be blank")
  @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d @$!%*?&]{8,}$",
      message = "Password must include at least one uppercase letter, one lowercase letter, one number, and one special character")
  private String password;
}
