package com.riwi.riwi_projects_system.users.application;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.riwi.riwi_projects_system.users.domain.Roles;
import com.riwi.riwi_projects_system.users.domain.UserEntity;
import com.riwi.riwi_projects_system.users.domain.UserRepository;
import com.riwi.riwi_projects_system.users.infrastructure.dtos.request.RegisterUserDto;

@Service
public class AuthService {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public void registerUser(RegisterUserDto registerUserDto) {
    // Check if user with the email exists
    Optional<UserEntity> foundUser = userRepository.findByEmail(registerUserDto.getEmail());
    if (foundUser.isPresent())
      throw new HttpClientErrorException(HttpStatus.CONFLICT,
          String.format("User with email '%s' already exists", registerUserDto.getEmail()));

    String encodedPassword = this.passwordEncoder.encode(registerUserDto.getPassword());
    Roles roleToRegister = Roles.valueOf(registerUserDto.getRole());
    var userEntity = UserEntity.builder().email(registerUserDto.getEmail()).fullname(registerUserDto.getFullname())
        .password(encodedPassword).role(roleToRegister).build();

    userEntity.setCreatedBy(userEntity);
    userEntity.setModifiedBy(userEntity);

    // Create user in the database
    var savedUserEntity = userRepository.save(userEntity);
    String message = String.format("User with email %s registered successfully", savedUserEntity.getEmail());
    logger.info(message);
  }

}