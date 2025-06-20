package com.safecube.safecube_backend.shared.exception.crypto;

import com.safecube.safecube_backend.shared.exception.ApplicationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CryptoOperationExceptionTest {

    @Test
    @DisplayName("Constructor should set message and cause correctly")
    void constructor_shouldSetMessageAndCause() {
        String expectedMessage = "Crypto operation failed";
        Throwable expectedCause = new RuntimeException("Specific crypto error");
        CryptoOperationException exception = new CryptoOperationException(expectedMessage, expectedCause);

        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
        assertThat(exception.getCause()).isEqualTo(expectedCause);
        assertThat(exception).isInstanceOf(ApplicationException.class);
    }
}