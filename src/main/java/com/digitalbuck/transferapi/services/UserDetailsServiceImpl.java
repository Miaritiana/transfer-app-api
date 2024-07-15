package com.digitalbuck.transferapi.services;

import com.digitalbuck.transferapi.model.Principal;
import com.digitalbuck.transferapi.repository.entity.Account;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String telephone) throws UsernameNotFoundException {
        Account account = accountService.getAccountByTelephone(telephone);
        if (Objects.isNull(account)) {
            throw new UsernameNotFoundException("User of telephone: " + telephone + " not found");
        }
        return Principal.builder().account(account).build();
    }
}
