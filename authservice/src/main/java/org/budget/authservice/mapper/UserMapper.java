package org.budget.authservice.mapper;

import org.budget.authservice.dto.UserDto;
import org.budget.authservice.model.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserDto toDto(User user);
    User fromDto(UserDto userDto);
}
