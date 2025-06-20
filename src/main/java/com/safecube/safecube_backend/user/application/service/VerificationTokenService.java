package com.safecube.safecube_backend.user.application.service;

import com.safecube.safecube_backend.user.domain.port.out.CreateVerificationTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service class for managing verification tokens.
 *
 * @author miguelrodriguez19
 * @since 0.2.0
 */
@Service
@RequiredArgsConstructor
public class VerificationTokenService {

    private final CreateVerificationTokenPort tokenPort;

    /**
     * Creates a new verification token for a given user.
     *
     * @param userId The UUID of the user for whom the token is being created.
     * @return The string representation of the generated verification token.
     */
    public String createToken(UUID userId) {
        UUID token = UUID.randomUUID();
        tokenPort.save(token, userId);
        return token.toString();
    }
}
