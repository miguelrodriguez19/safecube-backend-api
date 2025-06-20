package com.safecube.safecube_backend.user.domain.port.out;

/**
 * Service for securely deriving password hashes using PBKDF2 with SHA-512.
 * Provides methods to generate salts and derive hashes.
 *
 * @author miguelrodriguez19
 * @since 0.2.0
 */
public interface PasswordDerivationService {

    /**
     * Generates a cryptographically secure random salt.
     *
     * @return A byte array representing the generated salt.
     */
    byte[] generateSalt();

    /**
     * Derives a cryptographic hash from a given PIN and salt.
     *
     * @param pin  The PIN to be hashed as a character array.
     * @param salt The salt to be used in the hashing process as a byte array.
     * @return A byte array representing the derived hash.
     */
    byte[] deriveHash(char[] pin, byte[] salt);
}
