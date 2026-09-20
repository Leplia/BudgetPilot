package org.budget.authservice.mapper;

import org.budget.authservice.dto.CredentialDto;
import org.budget.authservice.model.Credential;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CredentialsMapper {
    CredentialDto toDto(Credential credential);
    Credential fromDto(CredentialDto credentialDto);
}
