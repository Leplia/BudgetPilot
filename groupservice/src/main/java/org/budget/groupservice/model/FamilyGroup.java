package org.budget.groupservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.budget.commonaudit.audit.Auditable;

import java.util.*;

@Entity
@Table(name="family_groups")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FamilyGroup extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "familyGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GroupMember> members = new HashSet<>();

    @OneToMany(mappedBy = "familyGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invitation> invitations;
}
