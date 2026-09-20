package org.budget.authservice.dto;

import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private String firstName;
    private String lastName;
    private String country;
    private String photoUrl;
}
