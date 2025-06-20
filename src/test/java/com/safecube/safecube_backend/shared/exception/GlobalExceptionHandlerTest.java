package com.safecube.safecube_backend.shared.exception;

import com.safecube.safecube_backend.shared.exception.crypto.CryptoOperationException;
import com.safecube.safecube_backend.shared.exception.user.EmailAlreadyTakenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    @DisplayName("onCrypto should return INTERNAL_SERVER_ERROR with CRYPTO_FAILURE code")
    void onCrypto_shouldReturnInternalServerError() {
        CryptoOperationException ex = new CryptoOperationException("Crypto failed", new RuntimeException("cause"));
        ResponseEntity<ApiError> responseEntity = globalExceptionHandler.onCrypto(ex);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().code()).isEqualTo("CRYPTO_FAILURE");
        assertThat(responseEntity.getBody().message()).isEqualTo("Crypto failed");
        assertThat(responseEntity.getBody().timestamp()).isNotNull().isBeforeOrEqualTo(Instant.now());
    }

    @Test
    @DisplayName("onEmailTaken should return CONFLICT with EMAIL_IN_USE code")
    void onEmailTaken_shouldReturnConflict() {
        String email = "test@example.com";
        EmailAlreadyTakenException ex = new EmailAlreadyTakenException(email);
        ResponseEntity<ApiError> responseEntity = globalExceptionHandler.onEmailTaken(ex);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().code()).isEqualTo("EMAIL_IN_USE");
        assertThat(responseEntity.getBody().message()).isEqualTo("Email already in use: " + email);
        assertThat(responseEntity.getBody().timestamp()).isNotNull().isBeforeOrEqualTo(Instant.now());
    }

    @Test
    @DisplayName("onGeneric should return INTERNAL_SERVER_ERROR with UNEXPECTED_ERROR code")
    void onGeneric_shouldReturnInternalServerError() {
        Exception ex = new Exception("Some generic error");
        ResponseEntity<ApiError> responseEntity = globalExceptionHandler.onGeneric(ex);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().code()).isEqualTo("UNEXPECTED_ERROR");
        assertThat(responseEntity.getBody().message()).isEqualTo("Some generic error");
        assertThat(responseEntity.getBody().timestamp()).isNotNull().isBeforeOrEqualTo(Instant.now());
    }
}