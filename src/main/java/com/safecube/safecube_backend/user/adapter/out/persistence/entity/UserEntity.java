package com.safecube.safecube_backend.user.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

/**
 * Represents a User entity in the database.
 *
 * @author miguelrodriguez19
 * @since 0.1.0
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    /**
     * The unique identifier for the user.
     */
    @Id
    private UUID id;

    /**
     * The email address of the user. This is a required field and has a maximum length of 320 characters.
     */
    @Column(nullable = false, length = 320)
    private String email;

    /**
     * The salt used for hashing the user's PIN. This is a required field.
     */
    @Column(name = "pin_salt", nullable = false)
    private byte[] pinSalt;

    /**
     * The hashed version of the user's PIN. This is a required field.
     */
    @Column(name = "pin_hash", nullable = false)
    private byte[] pinHash;

    /**
     * Indicates whether the user's email address has been verified. Defaults to false.
     */
    @Column(name = "email_verified", nullable = false)
    @Builder.Default
    private boolean emailVerified = false;

    /**
     * The number of failed login attempts for the user. Defaults to 0.
     */
    @Column(name = "failed_login_attempts", nullable = false)
    @Builder.Default
    private int failedLoginAttempts = 0;

    /**
     * The timestamp until which the user's account is locked. Can be null if the account is not locked.
     */
    @Column(name = "locked_until")
    private Instant lockedUntil;

    /**
     * The timestamp when the user entity was created. This field is automatically populated upon creation
     * and is not updatable.
     */
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    /**
     * The timestamp when the user entity was last updated. This field is automatically populated upon updates.
     */
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    /**
     * The timestamp when the user accepted the terms and conditions. This is a required field and is not updatable.
     */
    @Column(name = "terms_accepted_at", nullable = false, updatable = false)
    private Instant termsAcceptedAt;
}
