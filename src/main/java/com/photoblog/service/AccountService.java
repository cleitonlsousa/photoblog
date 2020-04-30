package com.photoblog.service;

import com.photoblog.api.request.AccountRequest;
import com.photoblog.exception.EmailExistsException;
import com.photoblog.model.Account;
import com.photoblog.respository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    private Boolean existsByEmail(String email){
        return this.accountRepository.existsByEmail(email);
    }

    protected Account getByEmail(String email){
        return accountRepository.findByEmail(email);
    }

    public void save(AccountRequest accountRequest){

        if (existsByEmail(accountRequest.getEmail()))
            throw new EmailExistsException(accountRequest.getEmail());

        accountRepository.save(new Account(
                accountRequest.getFirstName(),
                accountRequest.getLastName(),
                accountRequest.getEmail(),
                new BCryptPasswordEncoder().encode(accountRequest.getSecret())
        ));
    }

}
