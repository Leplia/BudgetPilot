package org.budget.groupservice.dto;

import lombok.*;
import org.budget.groupservice.model.GroupRole;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvitationDto {
    private FamilyGroupInfoDto familyGroupInfoDto;
    private GroupRole invitedRole;
    private String invitedEmail;
    //private UserDto user;
}
