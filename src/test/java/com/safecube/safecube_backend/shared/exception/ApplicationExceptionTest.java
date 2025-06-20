package com.safecube.safecube_backend.shared.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationExceptionTest {

    // Subclase concreta para probar los constructores de ApplicationException
    private static class ConcreteApplicationException extends ApplicationException {
        public ConcreteApplicationException(String message) {
            super(message);
        }

        public ConcreteApplicationException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @Test
    @DisplayName("Constructor with message should set message correctly")
    void constructorWithMessage_shouldSetMessage() {
        String expectedMessage = "Test exception message";
        ConcreteApplicationException exception = new ConcreteApplicationException(expectedMessage);

        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
        assertThat(exception.getCause()).isNull();
        assertThat(exception).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Constructor with message and cause should set them correctly")
    void constructorWithMessageAndCause_shouldSetMessageAndCause() {
        String expectedMessage = "Test exception message with cause";
        Throwable expectedCause = new RuntimeException("Root cause");
        ConcreteApplicationException exception = new ConcreteApplicationException(expectedMessage, expectedCause);

        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
        assertThat(exception.getCause()).isEqualTo(expectedCause);
        assertThat(exception).isInstanceOf(RuntimeException.class);
    }
}