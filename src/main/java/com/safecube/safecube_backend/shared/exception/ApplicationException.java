package com.safecube.safecube_backend.shared.exception;

/**
 * Base class for all custom runtime exceptions in Safe Cube.
 * Extends {@link RuntimeException} so transactions are rolled back automatically.
 *
 * @author miguelrodriguez19
 * @since 0.2.0
 */
public abstract class ApplicationException extends RuntimeException {

    /**
     * Constructs a
     * @param message The detail message.
     */
    protected ApplicationException(String message) {
        super(message);
    }

    /**
     * Constructs a new ApplicationException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause of the exception.
     */
    protected ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
