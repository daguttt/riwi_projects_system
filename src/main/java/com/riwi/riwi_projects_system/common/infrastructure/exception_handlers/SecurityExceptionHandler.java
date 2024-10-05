package com.riwi.riwi_projects_system.common.infrastructure.exception_handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SecurityExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @ExceptionHandler(BadCredentialsException.class)
  public ProblemDetail handleBadCredentialException(BadCredentialsException exception) {
    logger.debug(exception.getMessage());
    return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, "Invalid email or password");
  }
}
