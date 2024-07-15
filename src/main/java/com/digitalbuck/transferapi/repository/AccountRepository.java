package com.digitalbuck.transferapi.repository;

import com.digitalbuck.transferapi.repository.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
  Optional<Account> findByUsername(String username);

  Optional<Account> findByTelephone(String telephone);

  boolean existsByTelephone(String telephone);

}
