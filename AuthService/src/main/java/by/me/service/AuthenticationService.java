package by.me.service;

import by.me.dto.JWTAuthRequest;
import by.me.dto.JWTAuthResponse;
import by.me.dto.UserDTO;
import by.me.exceptions.UserNotFoundException;
import by.me.exceptions.WrongPasswordException;
import by.me.model.UserCredential;
import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import org.apache.catalina.User;

public interface AuthenticationService {
    JWTAuthResponse login(@NonNull JWTAuthRequest authRequest) throws UserNotFoundException, WrongPasswordException;
    void register(UserDTO userDTO) throws AuthException;

}
