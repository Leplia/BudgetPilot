package org.budget.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String login;
    private String email;
    private String password;
    private String country;
    private String firstName;
    private String lastName;
    private String photoUrl;
    private Boolean rememberMe;
}
