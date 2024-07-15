package com.digitalbuck.transferapi.controller.mapper;

import com.digitalbuck.transferapi.model.Account;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountMapper {
  private final PasswordEncoder encoder;

  public Account toDto(com.digitalbuck.transferapi.repository.entity.Account entity) {
    return Account.builder()
        .id(entity.getId())
        .username(entity.getUsername())
        .firstname(entity.getFirstname())
        .lastname(entity.getLastname())
        .telephone(entity.getTelephone())
        .balance(entity.getBalance())
        .currency(entity.getCurrency())
        .password(entity.getPassword())
        .build();
  }

  public com.digitalbuck.transferapi.repository.entity.Account toEntity(Account dto) {
    return com.digitalbuck.transferapi.repository.entity.Account.builder()
        .id(dto.getId())
        .username(dto.getUsername())
        .firstname(dto.getFirstname())
        .lastname(dto.getLastname())
        .telephone(dto.getTelephone())
        .balance(dto.getBalance())
        .currency(dto.getCurrency())
        .password(encoder.encode(dto.getPassword()))
        .build();
  }
}
