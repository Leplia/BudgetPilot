package org.budget.authservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.budget.commonaudit.audit.Auditable;

import java.util.UUID;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Credential credential;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="country", nullable = false)
    private String country;

    @Column(name="photo_url")
    private String photoUrl;
}
