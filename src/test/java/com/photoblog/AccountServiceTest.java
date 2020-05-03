package com.photoblog;

import com.photoblog.exception.AccountNFException;
import com.photoblog.model.Account;
import com.photoblog.respository.AccountRepository;
import com.photoblog.service.AccountService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AccountServiceTest {

    private AccountService accountService;
    private AccountRepository accountRepository;
    private final String email = "email@email.com";

    @Before
    public void before(){
        accountRepository = Mockito.mock(AccountRepository.class);
     this.accountService = new AccountService(accountRepository);
    }

    @Test
    public void find(){
        Account account = new Account();
        account.setFirstName("John");

        Mockito.when(accountService.findByEmail(Mockito.anyString())).thenReturn(account);
        Account response = accountService.findByEmail(email);

        Assert.assertEquals(account.getFirstName(), response.getFirstName());
    }

    @Test(expected = AccountNFException.class)
    public void notFind(){

        Mockito.when(accountRepository.findByEmail(Mockito.anyString())).thenThrow(new AccountNFException(email));
        accountService.findByEmail(email);
    }

}
