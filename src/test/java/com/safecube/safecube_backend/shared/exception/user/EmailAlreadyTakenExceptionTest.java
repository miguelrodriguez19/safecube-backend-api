package com.safecube.safecube_backend.shared.exception.user;

import com.safecube.safecube_backend.shared.exception.ApplicationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmailAlreadyTakenExceptionTest {

    @Test
    @DisplayName("Constructor should set the correct message based on email")
    void constructor_shouldSetCorrectMessage() {
        String email = "test@example.com";
        EmailAlreadyTakenException exception = new EmailAlreadyTakenException(email);

        String expectedMessage = "Email already in use: " + email;
        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
        assertThat(exception.getCause()).isNull();
        assertThat(exception).isInstanceOf(ApplicationException.class);
    }
}