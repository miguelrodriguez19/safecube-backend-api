package com.safecube.safecube_backend.user.application.service;

import com.safecube.safecube_backend.user.domain.port.out.CreateVerificationTokenPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link VerificationTokenService} class.
 */
@ExtendWith(MockitoExtension.class)
class VerificationTokenServiceTest {

    @Mock
    private CreateVerificationTokenPort tokenPort;

    @InjectMocks
    private VerificationTokenService verificationTokenService;

    private UUID testUserId;

    @BeforeEach
    void setUp() {
        testUserId = UUID.randomUUID();
    }

    @Test
    @DisplayName("createToken should generate a new token, save it, and return its string representation")
    void createToken_shouldGenerateAndSaveToken_andReturnTokenString() {
        ArgumentCaptor<UUID> tokenCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<UUID> userIdCaptor = ArgumentCaptor.forClass(UUID.class);

        String resultTokenString = verificationTokenService.createToken(testUserId);

        assertNotNull(resultTokenString, "The returned token string should not be null.");
        assertFalse(resultTokenString.isEmpty(), "The returned token string should not be empty.");

        verify(tokenPort, times(1)).save(tokenCaptor.capture(), userIdCaptor.capture());

        UUID generatedToken = tokenCaptor.getValue();
        UUID capturedUserId = userIdCaptor.getValue();

        assertNotNull(generatedToken, "The generated token passed to the port should not be null.");
        assertEquals(testUserId, capturedUserId, "The userId passed to the port should match the input userId.");
        assertEquals(generatedToken.toString(), resultTokenString,
                "The returned token string should match the string representation of the generated token.");

        assertTrue(resultTokenString.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"),
                "The returned token string should be a valid UUID format.");
    }

    @Test
    @DisplayName("createToken should call save on tokenPort with a non-null token")
    void createToken_shouldCallSaveWithNonNullToken() {
        // Act
        verificationTokenService.createToken(testUserId);

        // Assert
        // We use an ArgumentCaptor to ensure the token passed to save is not null.
        ArgumentCaptor<UUID> tokenCaptor = ArgumentCaptor.forClass(UUID.class);
        verify(tokenPort).save(tokenCaptor.capture(), eq(testUserId));
        assertNotNull(tokenCaptor.getValue(), "The token saved by the port should not be null.");
    }

    @Test
    @DisplayName("createToken should return a different token on subsequent calls")
    void createToken_shouldReturnDifferentTokensOnSubsequentCalls() {
        // Act
        String token1 = verificationTokenService.createToken(testUserId);
        String token2 = verificationTokenService.createToken(testUserId);

        // Assert
        assertNotNull(token1);
        assertNotNull(token2);
        assertNotEquals(token1, token2, "Subsequent calls to createToken should generate different tokens.");
        verify(tokenPort, times(2)).save(any(UUID.class), eq(testUserId));
    }
}