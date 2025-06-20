package com.safecube.safecube_backend.shared.exception.crypto;

import com.safecube.safecube_backend.shared.exception.ApplicationException;

/**
 * Thrown when a cryptographic operation (PBKDF2, AES-GCM, etc.) fails.
 *
 * @author miguelrodriguez19
 * @since 0.2.0
 */
public class CryptoOperationException extends ApplicationException {

    /**
     * Constructs a new CryptoOperationException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause of the exception.
     */
    public CryptoOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
