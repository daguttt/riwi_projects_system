package com.riwi.riwi_projects_system.users.infrastructure.dtos.response;

import com.riwi.riwi_projects_system.common.infrastructure.dtos.response.ApiResponseDto;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class RegisterResponseDto extends ApiResponseDto {
  public RegisterResponseDto(int value, String string) {
    super(value, string);
  }
}
