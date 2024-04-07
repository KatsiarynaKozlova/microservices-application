package by.me.service.impl;

import by.me.dto.JWTAuthRequest;
import by.me.dto.JWTAuthResponse;
import by.me.dto.UserDTO;
import by.me.exceptions.UserNotFoundException;
import by.me.exceptions.WrongPasswordException;
import by.me.model.UserCredential;
import by.me.repository.UserRepository;
import by.me.service.AuthenticationService;
import by.me.service.JWTProvider;
import jakarta.security.auth.message.AuthException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class DefaultAuthenticationService implements AuthenticationService {

    private final UserRepository userRepository;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JWTProvider jwtProvider;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    public JWTAuthResponse login(@NonNull JWTAuthRequest authRequest) throws UserNotFoundException, WrongPasswordException {
        UserCredential user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found by EMAIL"));
        if (user.getPassword().equals(passwordEncoder.encode(authRequest.getPassword()))) {
            String accessToken = jwtProvider.generateAccessToken(user);
            String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.getEmail(), refreshToken);
            return new JWTAuthResponse(accessToken, refreshToken);
        } else {
            throw new WrongPasswordException("Wrong password");
        }
    }
    @Override
    public void register(UserDTO userDTO) throws AuthException {
        if ( !userRepository.existsUserByEmail(userDTO.getEmail())) {
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            UserCredential user = modelMapper.map(userDTO, UserCredential.class);
            userRepository.save(user);
        }
        throw new AuthException("User already exists");
    }
}