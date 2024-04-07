package by.me.controller;

import by.me.dto.JWTAuthRequest;
import by.me.dto.JWTAuthResponse;
import by.me.dto.UserDTO;
import by.me.exceptions.UserNotFoundException;
import by.me.exceptions.WrongPasswordException;
import by.me.service.impl.DefaultAuthenticationService;
import jakarta.security.auth.message.AuthException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private DefaultAuthenticationService authenticationService;
    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> login(@RequestBody JWTAuthRequest authRequest) throws UserNotFoundException, WrongPasswordException {
        return ResponseEntity.ok(authenticationService.login(authRequest));
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public void register(@RequestBody UserDTO userDTO) throws AuthException {
        authenticationService.register(userDTO);
    }

}
