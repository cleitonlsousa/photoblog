package com.photoblog.api;

import com.photoblog.api.request.AccountRequest;
import com.photoblog.api.response.AccountResponse;
import com.photoblog.service.AccountService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public void post(@Valid @RequestBody AccountRequest accountRequest){
        accountService.save(accountRequest);
    }

    @GetMapping
    public AccountResponse get(@AuthenticationPrincipal UserDetails user){
        return accountService.getByEmail(user.getUsername());
    }

    @PutMapping
    public void update(@Valid @RequestBody AccountRequest accountRequest, @Parameter(hidden = true) @AuthenticationPrincipal UserDetails user){
        accountService.update(accountRequest, user.getUsername());
    }

    @DeleteMapping
    public void delete(@Parameter(hidden = true) @AuthenticationPrincipal UserDetails user){
        accountService.delete(user.getUsername());
    }
}
