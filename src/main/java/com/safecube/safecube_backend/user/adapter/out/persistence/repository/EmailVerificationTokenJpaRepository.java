package com.safecube.safecube_backend.user.adapter.out.persistence.repository;

import com.safecube.safecube_backend.user.adapter.out.persistence.entity.EmailVerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * JPA repository for managing {@link EmailVerificationTokenEntity} entities.
 *
 * @author miguelrodriguez19
 * @since 0.2.0
 */
public interface EmailVerificationTokenJpaRepository extends JpaRepository<EmailVerificationTokenEntity, UUID> {
}
