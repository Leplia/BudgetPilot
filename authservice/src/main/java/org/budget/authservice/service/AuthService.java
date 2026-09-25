package org.budget.authservice.service;

import org.budget.authservice.dto.AuthRequest;
import org.budget.authservice.dto.JwtResponse;
import org.budget.authservice.dto.RegisterRequest;
import org.jspecify.annotations.Nullable;

public interface AuthService {
    @Nullable JwtResponse register(RegisterRequest registerRequest);

    @Nullable JwtResponse login(AuthRequest authRequest);

    @Nullable JwtResponse refreshToken(long l);
}
