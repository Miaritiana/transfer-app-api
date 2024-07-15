package com.digitalbuck.transferapi.services;

import com.digitalbuck.transferapi.repository.AccountRepository;
import com.digitalbuck.transferapi.repository.entity.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {
  private final AccountRepository accountRepository;

  public Account getAccountByUsername(String username) {
    return accountRepository.findByUsername(username).orElse(null);
  }

  public Account getAccountByTelephone(String telephone) {
    return accountRepository.findByTelephone(telephone).orElse(null);
  }

  public List<Account> crupdateAccounts(List<Account> toCrupdate) {
    return accountRepository.saveAll(toCrupdate);
  }
}
