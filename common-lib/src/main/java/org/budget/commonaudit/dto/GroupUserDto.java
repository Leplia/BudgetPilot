package org.budget.commonaudit.dto;

import jakarta.persistence.Column;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GroupUserDto {
    private String firstName;
    private String lastName;
    private String country;
    private String photoUrl;
}
