package com.riwi.riwi_projects_system.common.infrastructure.dtos.response;

import org.springframework.http.ProblemDetail;

import lombok.Getter;

@Getter
public class ProblemDetailWithErrors extends ProblemDetail {
  private ProblemDetailFieldError[] errors;

  public ProblemDetailWithErrors(int status) {
    super(status);
  }
}
