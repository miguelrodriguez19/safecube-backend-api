package com.safecube.safecube_backend.user.adapter.out.persistence.repository;

import com.safecube.safecube_backend.user.adapter.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * JPA repository for managing {@link UserEntity} entities.
 *
 * @author miguelrodriguez19
 * @since 0.1.0
 */
@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {

    /**
     * Checks if a user with the given email exists, ignoring case.
     *
     * @param email The email to check for existence.
     * @return {@code true} if a user with the given email exists (case-insensitive),
     *         {@code false} otherwise.
     */
    boolean existsByEmailIgnoreCase(String email);
}
