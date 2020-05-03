package com.photoblog.api;

import com.photoblog.api.request.JwtRequest;
import com.photoblog.security.JwtAuthentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AuthenticationController {

    private final JwtAuthentication jwtAuthentication;

    public AuthenticationController(JwtAuthentication jwtAuthentication) {
        this.jwtAuthentication = jwtAuthentication;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        return ResponseEntity.ok(jwtAuthentication.authenticate(jwtRequest.getUsername(), jwtRequest.getPassword()));

    }

}
