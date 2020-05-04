package com.photoblog.security;

import com.photoblog.api.response.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthentication {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtToken;

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public JwtAuthentication(AuthenticationManager authenticationManager, JwtUtil jwtToken, UserDetailsServiceImpl userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtToken = jwtToken;
        this.userDetailsService = userDetailsService;
    }

    public JwtResponse authenticate(String username, String password) throws Exception {

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            String token = jwtToken.generateToken(userDetails);

            return new JwtResponse(token);

        } catch (DisabledException e) {

            throw new Exception("USER_DISABLED", e);

        } catch (BadCredentialsException e) {

            throw new Exception("INVALID_CREDENTIALS", e);

        }

    }
}
