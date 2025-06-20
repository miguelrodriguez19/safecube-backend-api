package com.safecube.safecube_backend.shared.exception.user;

import com.safecube.safecube_backend.shared.exception.ApplicationException;

/**
 * Thrown when a user tries to register with an email that already exists.
 * Mapped to HTTP 409 (CONFLICT).
 *
 * @author miguelrodriguez19
 * @since 0.0.3
 */
public class EmailAlreadyTakenException extends ApplicationException {

    /**
     * Constructs a new EmailAlreadyTakenException with the specified detail message and cause.
     *
     * @param email The email that was already taken.
     */
    public EmailAlreadyTakenException(String email) {
        super("Email already in use: " + email);
    }
}
