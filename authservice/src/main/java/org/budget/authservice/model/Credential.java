package org.budget.authservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="credentials")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Credential {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String login;
    private String email;
    private String password;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    private User user;

    @Column(name="refresh_token")
    private String refreshToken;
}
