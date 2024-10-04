package com.riwi.riwi_projects_system.users.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  public Optional<UserEntity> findByEmail(String email);
}