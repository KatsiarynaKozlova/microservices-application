package by.me.controller;

import by.me.dto.JWTAuthRequest;
import by.me.dto.JWTAuthResponse;
import by.me.service.impl.DefaultAuthenticationService;
import jakarta.security.auth.message.AuthException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private DefaultAuthenticationService authenticationService;
    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> login(@RequestBody JWTAuthRequest authRequest) throws AuthException {
        return ResponseEntity.ok(authenticationService.login(authRequest));
    }

}
