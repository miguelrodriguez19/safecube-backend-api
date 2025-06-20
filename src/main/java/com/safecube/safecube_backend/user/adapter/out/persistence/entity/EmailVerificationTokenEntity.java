package com.safecube.safecube_backend.user.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

/**
 * Represents an email verification token entity in the database.
 * This entity stores information about a token used to verify a user's email address.
 *
 * @author miguelrodriguez19
 * @since 0.0.2
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "email_verification_tokens")
public class EmailVerificationTokenEntity {

    /**
     * The unique identifier for the email verification token.
     */
    @Id
    private UUID token;

    /**
     * The user associated with this token.
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    /**
     * The timestamp when this token expires.
     */
    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

    /**
     * Indicates whether this token has been used.
     */
    @Column(nullable = false)
    private boolean used;

    /**
     * The timestamp when this token was created.
     */
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
}
