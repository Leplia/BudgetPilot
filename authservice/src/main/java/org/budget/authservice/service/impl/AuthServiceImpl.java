package org.budget.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.budget.authservice.dto.AuthRequest;
import org.budget.authservice.dto.JwtResponse;
import org.budget.authservice.dto.RegisterRequest;
import org.budget.authservice.mapper.CredentialsMapper;
import org.budget.authservice.model.Credential;
import org.budget.authservice.model.User;
import org.budget.authservice.repository.AuthRepository;
import org.budget.authservice.repository.UserRepository;
import org.budget.authservice.service.AuthService;
import org.budget.authservice.util.JwtTokenProvider;
import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final CredentialsMapper credentialsMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public @Nullable JwtResponse register(RegisterRequest registerRequest) {
        Credential credential=Credential.builder()
                .login(registerRequest.getLogin())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        Credential savedCredential = authRepository.save(credential);
        User user =User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .country(registerRequest.getCountry())
                .photoUrl(registerRequest.getPhotoUrl())
                .credential(savedCredential)
                .build();
        User savedUser = userRepository.save(user);
        savedCredential.setUser(user);
        authRepository.save(savedCredential);
        if(registerRequest.getRememberMe()){
            String refreshToken=jwtTokenProvider.generateRefreshToken(savedUser.getId());
            savedCredential.setRefreshToken(refreshToken);
            authRepository.save(savedCredential);
            return new JwtResponse(jwtTokenProvider.generateAccessToken(savedCredential.getId(), savedCredential.getLogin()), refreshToken);
        }
        return new JwtResponse(jwtTokenProvider.generateAccessToken(savedCredential.getId(), savedCredential.getLogin()), null);
    }

    @Override
    public @Nullable JwtResponse login(AuthRequest authRequest) {
        return null;
    }

    @Override
    public @Nullable JwtResponse refreshToken(long l) {
        return null;
    }
}
