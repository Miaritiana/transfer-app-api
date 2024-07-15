package com.digitalbuck.transferapi.services;

import com.digitalbuck.transferapi.model.Auth;
import com.digitalbuck.transferapi.model.Principal;
import com.digitalbuck.transferapi.repository.entity.Account;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AuthService {
    private final AccountService accountService;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final String AUTHORIZATION_HEADER = "Authorization";
    private final int BEARER_PREFIX_COUNT = 7;

    public String signIn(Auth toAuthenticate) {
        String telephone = toAuthenticate.getTelephone();
        UserDetails principal = userDetailsServiceImpl.loadUserByUsername(telephone);
        if (!passwordEncoder.matches(toAuthenticate.getPassword(), principal.getPassword())) {
            throw new BadCredentialsException("Wrong Password!");
        }
        return jwtService.generateToken(principal);
    }

    @Transactional
    public String signUp(Account account) {
        String telephone = account.getTelephone();
        Account existingAccount = accountService.getAccountByTelephone(telephone);
        if (Objects.nonNull(existingAccount)) {
            throw new DuplicateKeyException("User with the phone number: " + telephone + " already exists.");
        }
        String hashedPassword = passwordEncoder.encode(account.getPassword());
        Account createdAccount =
                accountService
                        .crupdateAccounts(
                                List.of(
                                        Account.builder()
                                                .username(account.getUsername())
                                                .telephone(account.getTelephone())
                                                .password(hashedPassword)
                                                .build()))
                        .get(0);
        Principal principal = Principal.builder().account(createdAccount).build();
        return jwtService.generateToken(principal);
    }

    public Account whoami(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        String token = authHeader.substring(BEARER_PREFIX_COUNT);
        String telephone = jwtService.extractEmail(token);
        return accountService.getAccountByTelephone(telephone);
    }
}
