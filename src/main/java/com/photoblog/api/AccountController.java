package com.photoblog.api;

import com.photoblog.api.request.AccountRequest;
import com.photoblog.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public void save(@RequestBody AccountRequest accountRequest){
        accountService.save(accountRequest);
    }
}
