package com.photoblog.api;

import com.photoblog.api.request.AccountRequest;
import com.photoblog.api.request.PostRequest;
import com.photoblog.api.response.AccountResponse;
import com.photoblog.service.AccountService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public void save(@Valid @RequestBody AccountRequest accountRequest){
        accountService.save(accountRequest);
    }

    @GetMapping
    public AccountResponse getByEmail(@AuthenticationPrincipal User user){
        return accountService.getByEmail(user.getUsername());
    }

    @PutMapping
    public void update(@Valid @RequestBody AccountRequest accountRequest, @AuthenticationPrincipal User user){
        accountService.update(accountRequest, user.getUsername());
    }
}
