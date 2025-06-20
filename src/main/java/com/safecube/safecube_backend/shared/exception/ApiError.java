package com.safecube.safecube_backend.shared.exception;

import java.time.Instant;

/**
 * Simple DTO returned by the global exception handler.
 *
 * @param code     machine-readable error code
 * @param message  human-readable description
 * @param timestamp ISO-8601 instant when the error was produced
 *
 * @author miguelrodriguez19
 * @since 0.0.3
 */
public record ApiError(String code, String message, Instant timestamp) { }
