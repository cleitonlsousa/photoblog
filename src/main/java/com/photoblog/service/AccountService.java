package com.photoblog.service;

import com.photoblog.api.request.AccountRequest;
import com.photoblog.api.response.AccountResponse;
import com.photoblog.exception.AccountNFException;
import com.photoblog.exception.EmailExistsException;
import com.photoblog.model.Account;
import com.photoblog.respository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    private Boolean existsByEmail(String email){
        return this.accountRepository.existsByEmail(email);
    }

    public Account findByEmail(String email){
        return accountRepository.findByEmail(email).orElseThrow(() -> new AccountNFException(email));
    }

    public Account save(AccountRequest accountRequest){

        if (existsByEmail(accountRequest.getEmail()))
            throw new EmailExistsException(accountRequest.getEmail());

        return accountRepository.save(new Account(
                accountRequest.getFirstName(),
                accountRequest.getLastName(),
                accountRequest.getEmail(),
                new BCryptPasswordEncoder().encode(accountRequest.getSecret())
        ));
    }

    public AccountResponse getByEmail(String email){
        Account account = findByEmail(email);

        return new AccountResponse(account.getFirstName(), account.getLastName(), account.getEmail());
    }

    public void update(AccountRequest accountRequest, String email){
        Account account = findByEmail(email);
        account.setFirstName(accountRequest.getFirstName());
        account.setLastName(accountRequest.getLastName());
        account.setSecret(new BCryptPasswordEncoder().encode(accountRequest.getSecret()));

        accountRepository.save(account);
    }

}
