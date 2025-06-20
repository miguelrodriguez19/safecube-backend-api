package com.safecube.safecube_backend.user.adapter.out.persistence;

import com.safecube.safecube_backend.user.adapter.out.persistence.entity.EmailVerificationTokenEntity;
import com.safecube.safecube_backend.user.adapter.out.persistence.entity.UserEntity;
import com.safecube.safecube_backend.user.adapter.out.persistence.repository.EmailVerificationTokenJpaRepository;
import com.safecube.safecube_backend.user.domain.port.out.CreateVerificationTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

/**
 * Adapter that implements the {@link CreateVerificationTokenPort} to save email verification tokens
 * to the database using a JPA repository.
 *
 * @author miguelrodriguez19
 * @since 0.2.0
 */
@Component
@RequiredArgsConstructor
public class CreateVerificationTokenAdapter implements CreateVerificationTokenPort {

    private final EmailVerificationTokenJpaRepository repository;

    @Override
    public void save(UUID token, UUID userId) {
        var entity = EmailVerificationTokenEntity.builder()
                .token(token)
                .user(UserEntity.builder()
                        .id(userId)
                        .build())
                .expiresAt(Instant.now().plus(24, ChronoUnit.HOURS))
                .used(false)
                .build();
        repository.save(entity);
    }
}


