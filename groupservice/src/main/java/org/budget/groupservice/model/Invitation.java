package org.budget.groupservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.budget.commonaudit.audit.Auditable;

import java.util.UUID;

@Entity
@Table(name="invitations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Invitation extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private FamilyGroup familyGroup;

    @Column(name = "invited_email", nullable = false)
    private String invitedEmail;

    @Enumerated(EnumType.STRING)
    @Column(name = "invited_role", nullable = false)
    private GroupRole invitedRole;

    @Column(name = "is_used", nullable = false)
    private Boolean isUsed;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InvitationStatus status;

    @Column(name = "accepted_by_user_id")
    private UUID acceptedByUserId;
}
