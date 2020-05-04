package com.photoblog;

import br.com.six2six.fixturefactory.Fixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.photoblog.api.request.AccountRequest;
import com.photoblog.api.request.JwtRequest;
import com.photoblog.api.response.JwtResponse;
import com.photoblog.fixture.FixtureLoader;
import com.photoblog.fixture.GenericFixture;
import com.photoblog.model.Account;
import com.photoblog.respository.AccountRepository;
import com.photoblog.security.JwtAuthentication;
import com.photoblog.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={ PhotoblogApplication.class , SpringSecurityTestConfig.class})
public class AccountMockTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mvc;

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private JwtAuthentication jwtAuthentication;

    @Before
    public void before() {

        FixtureLoader.loadTemplates();
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void create() throws Exception {

        AccountRequest accountRequest = Fixture.from(AccountRequest.class).gimme(GenericFixture.ACCOUNT_REQUEST_PETER);

        when(accountRepository.existsByEmail(GenericFixture.ACCOUNT_EMAIL_PETER)).thenReturn(false);

        mvc.perform(
                post("/account")
                        .content(new ObjectMapper().writeValueAsString(accountRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());

    }

    @Test
    @WithUserDetails(GenericFixture.ACCOUNT_EMAIL_PETER)
    public void createExist() throws Exception {

        AccountRequest accountRequest = Fixture.from(AccountRequest.class).gimme(GenericFixture.ACCOUNT_REQUEST_PETER);

        when(accountRepository.existsByEmail(GenericFixture.ACCOUNT_EMAIL_PETER)).thenReturn(true);

        mvc.perform(
                post("/account")
                        .content(new ObjectMapper().writeValueAsString(accountRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void authenticate() throws Exception {

        JwtRequest jwtRequest = Fixture.from(JwtRequest.class).gimme(GenericFixture.JWT_REQUEST);

        JwtResponse jwtResponse = Fixture.from(JwtResponse.class).gimme(GenericFixture.JWT_RESPONSE);

        when(jwtAuthentication.authenticate(anyString(), anyString())).thenReturn(jwtResponse);

        MvcResult mvcResult = mvc.perform(
                post("/authenticate")
                        .content(new ObjectMapper().writeValueAsString(jwtRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token", is(notNullValue())))
                .andExpect(jsonPath("$.token", is("token")))
                .andReturn();
    }

    @Test
    @WithUserDetails(GenericFixture.ACCOUNT_EMAIL_PETER)
    public void find() throws Exception {

       Account account = Fixture.from(Account.class).gimme(GenericFixture.ACCOUNT_PETER);

        when(accountRepository.findByEmail(GenericFixture.ACCOUNT_EMAIL_PETER)).thenReturn(Optional.of(account));

        mvc.perform(get("/account"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("peter")))
                .andExpect(jsonPath("$.lastName", is("parker")))
                .andExpect(jsonPath("$.email", is("peterparker@email.com")));
    }



    @Test
    @WithUserDetails(GenericFixture.ACCOUNT_EMAIL_PETER)
    public void update() throws Exception {

        AccountRequest accountRequest = Fixture.from(AccountRequest.class).gimme(GenericFixture.ACCOUNT_REQUEST_PETER);

        Account account = Fixture.from(Account.class).gimme(GenericFixture.ACCOUNT_PETER);

        when(accountRepository.findByEmail(GenericFixture.ACCOUNT_EMAIL_PETER)).thenReturn(Optional.of(account));

        mvc.perform(
                put("/account")
                        .content(new ObjectMapper().writeValueAsString(accountRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(GenericFixture.ACCOUNT_EMAIL_PETER)
    public void delleteAccount() throws Exception {

        Account account = Fixture.from(Account.class).gimme(GenericFixture.ACCOUNT_PETER);

        when(accountRepository.findByEmail(GenericFixture.ACCOUNT_EMAIL_PETER)).thenReturn(Optional.of(account));

        mvc.perform(delete("/account")).andExpect(status().isOk());

    }

}
