package com.safecube.safecube_backend.user.adapter.out.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.safecube.safecube_backend.user.adapter.out.persistence.entity.UserEntity;
import java.time.Instant;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * <h2>Integration test for {@link UserJpaRepository}</h2>
 *
 * <p>This test uses the <strong>JPA slice</strong> provided by {@link DataJpaTest}. Spring Boot
 * starts a lightweight application context with:</p>
 * <ul>
 *   <li>An <em>in-memory H2 database</em>-no external PostgreSQL instance is required.</li>
 *   <li>Hibernate auto-DDL to create the <code>users</code> table as declared in the entity.</li>
 *   <li>Only the persistence layer beans (repositories, entities, converters…); web and security
 *       layers are deliberately excluded for speed.</li>
 * </ul>
 *
 * <p>The test verifies the repository method
 * <code>existsByEmailIgnoreCase(String email)</code> behaves as expected, confirming that
 * the <em>functional requirement of case-insensitive uniqueness</em> for email addresses
 * is met at the database layer.</p>
 *
 * @author miguelrodriguez19
 * @since 0.2.0
 */
@DataJpaTest
class UserJpaRepositoryIT {

    @Autowired
    private UserJpaRepository repository;

    @Test
    @DisplayName("Save a user and verify case-insensitive email lookup")
    void existsByEmailIgnoreCase_positive() {
        // Arrange
        String email = "Miguel@example.com";
        repository.save(UserEntity.builder()
                .id(UUID.randomUUID())
                .email(email)
                .pinSalt(new byte[16])
                .pinHash(new byte[64])
                .termsAcceptedAt(Instant.now())
                .build());

        // Act & Assert
        assertThat(repository.existsByEmailIgnoreCase("miguel@EXAMPLE.com")).isTrue();
        assertThat(repository.existsByEmailIgnoreCase("bob@example.com")).isFalse();
    }
}
