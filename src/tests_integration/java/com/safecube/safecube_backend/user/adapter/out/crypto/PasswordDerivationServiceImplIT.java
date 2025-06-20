package com.safecube.safecube_backend.user.adapter.out.crypto;

import com.safecube.safecube_backend.user.domain.port.out.PasswordDerivationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <h2>Integration test for {@link PasswordDerivationServiceImpl}</h2>
 *
 * <p>Bootstraps a minimal Spring Boot context with inline properties that mimic the
 * <em>local</em> profile. The test verifies that:</p>
 *
 * <ul>
 *   <li>The {@code PasswordDerivationServiceImpl} bean is created by the container.</li>
 *   <li>Configuration properties injected via {@code @Value} are honoured, producing
 *       a salt of <em>16 bytes</em> and a hash of <em>64 bytes</em>.</li>
 * </ul>
 *
 * @author miguelrodriguez19
 * @since 0.2.0
 */
@SpringBootTest(properties = {
        "crypto.password.derivation.salt.length=16",
        "crypto.password.derivation.hash.length=64",
        "crypto.password.derivation.iterations=310000",
        "crypto.password.derivation.algorithm=PBKDF2WithHmacSHA512"
})
class PasswordDerivationServiceImplIT {

    @Autowired
    private PasswordDerivationService service;

    @Test
    void shouldUseInjectedParameters() {
        // bean exists
        assertThat(service).isNotNull();

        // behaviour depends on injected values
        byte[] salt = service.generateSalt();
        assertThat(salt).hasSize(16);

        byte[] hash = service.deriveHash("1234".toCharArray(), salt);
        assertThat(hash).hasSize(64);
    }
}

