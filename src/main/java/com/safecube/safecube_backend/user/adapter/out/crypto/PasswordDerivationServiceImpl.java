package com.safecube.safecube_backend.user.adapter.out.crypto;

import com.safecube.safecube_backend.shared.exception.crypto.CryptoOperationException;
import com.safecube.safecube_backend.user.domain.port.out.PasswordDerivationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

/**
 * Implementation of PasswordDerivationService using PBKDF2 with Hmac SHA-512.
 * Configured via application properties (salt length, iterations, etc).
 *
 * @author miguelrodriguez19
 * @since 0.2.0
 */
@Component
public class PasswordDerivationServiceImpl implements PasswordDerivationService {

    private final int saltLength;
    private final int hashLength;
    private final int iterations;
    private final String algorithm;

    private final SecureRandom secureRandom = new SecureRandom();

    public PasswordDerivationServiceImpl(
            @Value("${crypto.password.derivation.salt.length}") int saltLength,
            @Value("${crypto.password.derivation.hash.length}") int hashLength,
            @Value("${crypto.password.derivation.iterations}") int iterations,
            @Value("${crypto.password.derivation.algorithm}") String algorithm
    ) {
        this.saltLength = saltLength;
        this.hashLength = hashLength;
        this.iterations = iterations;
        this.algorithm = algorithm;
    }

    @Override
    public byte[] generateSalt() {
        byte[] salt = new byte[saltLength];
        secureRandom.nextBytes(salt);
        return salt;
    }

    @Override
    public byte[] deriveHash(char[] pin, byte[] salt) {
        try {
            KeySpec spec = new PBEKeySpec(pin, salt, iterations, hashLength * 8);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
            return factory.generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new CryptoOperationException("Failed to derive " + algorithm + " hash", e);
        }
    }
}
