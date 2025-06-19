package com.safecube.safecube_backend.user.adapter.out.persistence.repository;

import com.safecube.safecube_backend.user.adapter.out.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserJpaRepositoryTest {

    @Autowired
    private UserJpaRepository repository;

    @Test
    void existsByEmailIgnoreCase_positive() {
        final var email = "Miguel@example.com";
        repository.save(UserEntity.builder()
                .id(UUID.randomUUID())
                .email(email)
                .pinSalt(new byte[16])
                .pinHash(new byte[64])
                .termsAcceptedAt(Instant.now())
                .build());

        assertThat(repository.existsByEmailIgnoreCase("miguel@EXAMPLE.com")).isTrue();
        assertThat(repository.existsByEmailIgnoreCase("bob@example.com")).isFalse();
    }
}

