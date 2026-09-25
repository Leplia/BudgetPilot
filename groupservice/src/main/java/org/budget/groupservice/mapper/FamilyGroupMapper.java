package org.budget.groupservice.mapper;

import org.budget.groupservice.dto.FamilyGroupDto;
import org.budget.groupservice.dto.FamilyGroupInfoDto;
import org.budget.groupservice.model.FamilyGroup;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring",
        uses = {InvitationMapper.class, GroupMemberMapper.class})
@Component
public interface FamilyGroupMapper {
    FamilyGroupDto toDto(FamilyGroup familyGroup);
    FamilyGroup fromDto(FamilyGroupDto familyGroupDto);
    FamilyGroupInfoDto infoToDto(FamilyGroup familyGroup);
}
