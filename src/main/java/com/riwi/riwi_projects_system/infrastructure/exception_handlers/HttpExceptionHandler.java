package com.riwi.riwi_projects_system.infrastructure.exception_handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class HttpExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @ExceptionHandler(HttpClientErrorException.class)
  public ProblemDetail handleHttpExceptions(HttpClientErrorException exception) {
    logger.debug(exception.getStatusText());
    return ProblemDetail.forStatusAndDetail(exception.getStatusCode(), exception.getStatusText());
  }
}
