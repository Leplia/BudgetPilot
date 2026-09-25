package org.budget.groupservice.mapper;

import org.budget.groupservice.model.GroupMember;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface GroupMemberMapper {
    //GroupMember fromDto(GroupMemberDto groupMemberDto);
    //GroupMemberDto toDto(GroupMember groupMember);
}
