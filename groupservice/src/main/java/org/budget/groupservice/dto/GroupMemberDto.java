package org.budget.groupservice.dto;

import lombok.*;
import org.budget.commonaudit.dto.GroupUserDto;
import org.budget.groupservice.model.GroupRole;
import org.budget.groupservice.model.MemberStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GroupMemberDto {
    private GroupUserDto groupUserDto;
    private GroupRole role;
    private MemberStatus status;
    private FamilyGroupInfoDto familyGroupInfoDto;
}
