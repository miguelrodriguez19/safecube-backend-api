package com.safecube.safecube_backend.user.adapter.out.crypto;

import com.safecube.safecube_backend.shared.exception.crypto.CryptoOperationException;
import com.safecube.safecube_backend.user.domain.port.out.PasswordDerivationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the {@link PasswordDerivationServiceImpl} class.
 * Pure unit test – no Spring context, parameters passed by hand.
 */
class PasswordDerivationServiceImplUnitTest {

    private PasswordDerivationService service;

    @BeforeEach
    void setUp() {
        service = new PasswordDerivationServiceImpl(
                16, // saltLength
                64, // hashLength
                310_000, // iterations
                "PBKDF2WithHmacSHA512"); // algorithm
    }

    @Test
    @DisplayName("generateSalt should return a salt of the configured length")
    void shouldGenerateSaltOfCorrectLength() {
        assertThat(service.generateSalt()).hasSize(16);
    }

    @Test
    @DisplayName("deriveHash should produce a hash of the configured length")
    void shouldDeriveHashOfCorrectLength() {
        byte[] salt = service.generateSalt();
        assertThat(service.deriveHash("1234".toCharArray(), salt)).hasSize(64);
    }

    @Test
    @DisplayName("deriveHash should be idempotent for the same PIN and salt")
    void shouldBeIdempotentForSameInput() {
        byte[] salt = service.generateSalt();
        byte[] h1 = service.deriveHash("5678".toCharArray(), salt);
        byte[] h2 = service.deriveHash("5678".toCharArray(), salt);
        assertThat(h1).isEqualTo(h2);
    }

    @Test
    @DisplayName("deriveHash should produce different hashes for the same PIN but different salts")
    void shouldChangeHashIfSaltChanges() {
        byte[] h1 = service.deriveHash("0000".toCharArray(), service.generateSalt());
        byte[] h2 = service.deriveHash("0000".toCharArray(), service.generateSalt());
        assertThat(h1).isNotEqualTo(h2);
    }

    @Test
    @DisplayName("deriveHash should produce different hashes for different PINs with the same salt")
    void shouldChangeHashIfPinChanges() {
        byte[] salt = service.generateSalt();
        byte[] h1 = service.deriveHash("1111".toCharArray(), salt);
        byte[] h2 = service.deriveHash("2222".toCharArray(), salt);
        assertThat(h1).isNotEqualTo(h2);
    }

    @Test
    @DisplayName("deriveHash should throw exception when the cryptographic algorithm is invalid")
    void shouldThrowIllegalStateExceptionWhenAlgorithmIsInvalid() {
        // Arrange
        PasswordDerivationService serviceWithInvalidAlgorithm = new PasswordDerivationServiceImpl(
                16,
                64,
                1000,
                "DummyAlgorithmName123"
        );
        char[] testPin = "testPin".toCharArray();
        byte[] dummySalt = new byte[16]; // Dummy salt

        // Act & Assert
        var exception = assertThrows(CryptoOperationException.class, () -> {
            serviceWithInvalidAlgorithm.deriveHash(testPin, dummySalt);
        });

        assertThat(exception.getMessage()).isEqualTo("Failed to derive DummyAlgorithmName123 hash");
        assertThat(exception.getCause()).isInstanceOf(NoSuchAlgorithmException.class);
        assertThat(exception.getCause().getMessage()).contains("DummyAlgorithmName123");
    }
}