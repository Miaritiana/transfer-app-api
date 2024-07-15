package com.digitalbuck.transferapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
  private String id;
  private String username;
  private String firstname;
  private String lastname;
  private String telephone;
  private double balance;
  private String currency;
  private String password;
}
