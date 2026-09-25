package org.budget.groupservice.dto;

import jdk.jfr.Name;
import lombok.*;
import org.budget.groupservice.model.GroupMember;
import org.budget.groupservice.model.Invitation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FamilyGroupDto {
    private String name;
    private Set<GroupMemberDto> members;
    private List<InvitationDto> invitations;
}
