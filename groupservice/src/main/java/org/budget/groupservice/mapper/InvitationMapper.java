package org.budget.groupservice.mapper;

import org.budget.groupservice.dto.InvitationDto;
import org.budget.groupservice.model.Invitation;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring",
            uses={FamilyGroupMapper.class})
@Component
public interface InvitationMapper {
    InvitationDto toDto(Invitation invitation);
    Invitation fromDto(InvitationDto invitationDto);
}
