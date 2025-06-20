package com.safecube.safecube_backend.user.domain.port.out;

import java.util.UUID;

/**
 * Port for creating and saving a verification token for a user.
 *
 * @author miguelrodriguez19
 * @since 0.2.0
 */
public interface CreateVerificationTokenPort {

    /**
     * Saves a verification token associated with a user.
     *
     * @param token  The verification token to save.
     * @param userId The ID of the user the token belongs to.
     */
    void save(UUID token, UUID userId);
}
