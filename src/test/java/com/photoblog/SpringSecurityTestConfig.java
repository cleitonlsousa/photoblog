package com.photoblog;

import com.photoblog.security.UserDetailsImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

@TestConfiguration
public class SpringSecurityTestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        UserDetailsImpl peter = new UserDetailsImpl(
                1, "peterparker@email.com", "123456", true);

        UserDetailsImpl tony = new UserDetailsImpl(
                2, "tonystark@email.com", "123456", true);

        return new InMemoryUserDetailsManager(Arrays.asList(
                peter, tony
        ));
    }
}
