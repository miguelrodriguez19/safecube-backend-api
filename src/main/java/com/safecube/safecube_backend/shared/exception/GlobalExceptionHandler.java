package com.safecube.safecube_backend.shared.exception;

import com.safecube.safecube_backend.shared.exception.crypto.CryptoOperationException;
import com.safecube.safecube_backend.shared.exception.user.EmailAlreadyTakenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

/**
 * Centralised handler that converts domain/application exceptions
 * into consistent JSON responses.
 *
 * <p>Only the web/REST layer should depend on this class; deeper layers
 * simply throw their custom exceptions.</p>
 *
 * @author miguelrodriguez19
 * @since 0.2.1
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CryptoOperationException.class)
    public ResponseEntity<ApiError> onCrypto(CryptoOperationException ex) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "CRYPTO_FAILURE", ex);
    }

    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseEntity<ApiError> onEmailTaken(EmailAlreadyTakenException ex) {
        return build(HttpStatus.CONFLICT, "EMAIL_IN_USE", ex);
    }

    /**
     * Fallback for any uncaught exception.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> onGeneric(Exception ex) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "UNEXPECTED_ERROR", ex);
    }

    private ResponseEntity<ApiError> build(HttpStatus status, String code, Exception ex) {
        log.error("{}: {}", code, ex.getMessage(), ex);
        ApiError body = new ApiError(code, ex.getMessage(), Instant.now());
        return ResponseEntity.status(status).body(body);
    }
}
