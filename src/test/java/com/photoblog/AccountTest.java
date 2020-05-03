package com.photoblog;

import br.com.six2six.fixturefactory.Fixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.photoblog.api.request.AccountRequest;
import com.photoblog.api.request.JwtRequest;
import com.photoblog.fixture.FixtureLoader;
import com.photoblog.fixture.GenericFixture;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeClass
    public static void setupTest() {
        FixtureLoader.loadTemplates();
    }


    @Test
    public void create() throws Exception {

        AccountRequest accountRequest = Fixture.from(AccountRequest.class).gimme(GenericFixture.ACCOUNT_REQUEST_CREATE);

        mockMvc.perform(
                post("/account")
                        .content(new ObjectMapper().writeValueAsString(accountRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());

    }

    @Test
    public void createException() throws Exception {

        AccountRequest accountRequest = Fixture.from(AccountRequest.class).gimme(GenericFixture.ACCOUNT_REQUEST_CREATE);

        mockMvc.perform(
                post("/account")
                        .content(new ObjectMapper().writeValueAsString(accountRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is4xxClientError());

    }

    private String authenticate(JwtRequest jwtRequest) throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                post("/authenticate")
                        .content(new ObjectMapper().writeValueAsString(jwtRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token",is(notNullValue())))
                .andReturn();

        return JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.token");

    }


    @Test
    public void authenticateTest() throws Exception {

        JwtRequest jwtRequest = Fixture.from(JwtRequest.class).gimme(GenericFixture.JWT_REQUEST);

        Assert.assertNotNull(authenticate(jwtRequest));
    }

    @Test
    public void find() throws Exception {

        JwtRequest jwtRequest = Fixture.from(JwtRequest.class).gimme(GenericFixture.JWT_REQUEST);

        mockMvc.perform(
                get("/account")
                        .header("Authorization", "Bearer " + authenticate(jwtRequest))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("john")))
                .andExpect(jsonPath("$.lastName", is("doe")))
                .andExpect(jsonPath("$.email", is("johndoe@email.com")));
    }

    @Test
    public void update() throws Exception {
        JwtRequest jwtRequest = Fixture.from(JwtRequest.class).gimme(GenericFixture.JWT_REQUEST);
        AccountRequest accountRequest = Fixture.from(AccountRequest.class).gimme(GenericFixture.ACCOUNT_REQUEST_UPDATE);

        mockMvc.perform(
                put("/account")
                        .header("Authorization", "Bearer " + authenticate(jwtRequest))
                        .content(new ObjectMapper().writeValueAsString(accountRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void findUpdate() throws Exception {

        JwtRequest jwtRequest = Fixture.from(JwtRequest.class).gimme(GenericFixture.JWT_REQUEST);

        mockMvc.perform(
                get("/account")
                        .header("Authorization", "Bearer " + authenticate(jwtRequest))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("john")))
                .andExpect(jsonPath("$.lastName", is("Rambo")))
                .andExpect(jsonPath("$.email", is("johndoe@email.com")));
    }

    @Test
    public void delete() throws Exception {
        JwtRequest jwtRequest = Fixture.from(JwtRequest.class).gimme(GenericFixture.JWT_REQUEST);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + authenticate(jwtRequest));

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.delete("/account").headers(headers);

        this.mockMvc.perform(builder).andExpect(status().isOk());

    }

    @Test
    public void findDelete() throws Exception {

        JwtRequest jwtRequest = Fixture.from(JwtRequest.class).gimme(GenericFixture.JWT_REQUEST);

        mockMvc.perform(
                post("/authenticate")
                        .content(new ObjectMapper().writeValueAsString(jwtRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is4xxClientError());

    }
}
