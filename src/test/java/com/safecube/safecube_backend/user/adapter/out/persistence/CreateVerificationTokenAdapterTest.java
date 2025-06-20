package com.safecube.safecube_backend.user.adapter.out.persistence;

import com.safecube.safecube_backend.user.adapter.out.persistence.entity.EmailVerificationTokenEntity;
import com.safecube.safecube_backend.user.adapter.out.persistence.repository.EmailVerificationTokenJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateVerificationTokenAdapterTest {

    @Mock
    private EmailVerificationTokenJpaRepository repository;

    @InjectMocks
    private CreateVerificationTokenAdapter createVerificationTokenAdapter;

    @Captor
    private ArgumentCaptor<EmailVerificationTokenEntity> entityCaptor;

    @Test
    void save_shouldCreateAndSaveTokenWithCorrectDetails() {
        UUID token = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        Instant beforeCall = Instant.now();

        createVerificationTokenAdapter.save(token, userId);

        Instant afterCall = Instant.now();

        verify(repository, times(1)).save(entityCaptor.capture());

        EmailVerificationTokenEntity capturedEntity = entityCaptor.getValue();
        assertNotNull(capturedEntity, "Captured entity should not be null.");
        assertEquals(token, capturedEntity.getToken(), "Token in the entity should match the input token.");
        assertEquals(userId, capturedEntity.getUser().getId(), "UserId in the entity should match the input userId.");
        assertFalse(capturedEntity.isUsed(), "The 'used' flag should be false for a new token.");

        Instant expectedMinExpiry = beforeCall.plus(24, ChronoUnit.HOURS);
        Instant expectedMaxExpiry = afterCall.plus(24, ChronoUnit.HOURS);

        assertTrue(!capturedEntity.getExpiresAt().isBefore(expectedMinExpiry)
                && !capturedEntity.getExpiresAt().isAfter(expectedMaxExpiry),
                "ExpiresAt (" + capturedEntity.getExpiresAt() + ") should be approximately 24 hours from the call time, "
                        + "expected between " + expectedMinExpiry + " and " + expectedMaxExpiry + ".");
    }
}