package com.riwi.riwi_projects_system.common.infrastructure.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.riwi.riwi_projects_system.users.domain.UserEntity;

public class UserDetailsImpl implements UserDetails {

  private final UserEntity userEntity;

  public UserDetailsImpl(UserEntity userEntity) {
    this.userEntity = userEntity;
  }

  @Override
  public String getUsername() {
    return this.userEntity.getEmail();
  }

  @Override
  public String getPassword() {
    return this.userEntity.getPassword();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singleton(new SimpleGrantedAuthority(userEntity.getRole().name()));
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

}
