package org.budget.authservice.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CredentialDto {
    private String login;
    private String email;
}
