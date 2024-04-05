package by.me.service;

import by.me.dto.JWTAuthRequest;
import by.me.dto.JWTAuthResponse;
import by.me.dto.UserDTO;
import by.me.model.UserCredential;
import jakarta.security.auth.message.AuthException;
import lombok.NonNull;

public interface AuthenticationService {
    JWTAuthResponse login(@NonNull JWTAuthRequest authRequest) throws AuthException;
    JWTAuthResponse getAccessToken(@NonNull String refreshToken);
    JWTAuthResponse refresh(@NonNull String refreshToken) throws AuthException;


}
