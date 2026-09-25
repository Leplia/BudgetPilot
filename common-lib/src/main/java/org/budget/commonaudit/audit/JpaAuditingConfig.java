package org.budget.commonaudit.audit;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

@AutoConfiguration
@EnableJpaAuditing
public class JpaAuditingConfig {
    @Bean
    public AuditorAware<UUID> auditorProvider() {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext())
                .map(ctx -> ctx.getAuthentication())
                .map(auth -> (UUID) auth.getPrincipal());
    }
}