package org.budget.authservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
}
