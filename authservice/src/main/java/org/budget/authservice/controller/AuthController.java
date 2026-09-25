package org.budget.authservice.controller;

import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.budget.authservice.dto.AuthRequest;
import org.budget.authservice.dto.CredentialDto;
import org.budget.authservice.dto.JwtResponse;
import org.budget.authservice.dto.RegisterRequest;
import org.budget.authservice.model.User;
import org.budget.authservice.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(authService.login(authRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refresh(@RequestHeader("X-User-Id") String id){
        return ResponseEntity.ok(authService.refreshToken(Long.parseLong(id)));
    }
}
