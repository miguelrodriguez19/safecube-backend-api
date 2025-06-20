package com.safecube.safecube_backend.shared.exception;

import com.safecube.safecube_backend.shared.exception.crypto.CryptoOperationException;
import com.safecube.safecube_backend.shared.exception.user.EmailAlreadyTakenException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {GlobalExceptionHandlerIT.ExceptionTestController.class, GlobalExceptionHandler.class})
class GlobalExceptionHandlerIT {

    @Autowired
    private MockMvc mockMvc;

    // Test controller to throw exceptions
    @RestController
    @RequestMapping("/test-exceptions")
    protected static class ExceptionTestController {
        @GetMapping("/crypto")
        public void throwCryptoException() {
            throw new CryptoOperationException("Crypto test error", new RuntimeException("Root cause"));
        }

        @GetMapping("/email-taken")
        public void throwEmailTakenException() {
            throw new EmailAlreadyTakenException("test@example.com");
        }

        @GetMapping("/generic")
        public void throwGenericException() {
            throw new RuntimeException("Generic test error");
        }
    }

    @Test
    @DisplayName("GET /test-exceptions/crypto should be handled by GlobalExceptionHandler and return 500")
    void whenCryptoException_thenReturns500() throws Exception {
        mockMvc.perform(get("/test-exceptions/crypto")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value("CRYPTO_FAILURE"))
                .andExpect(jsonPath("$.message").value("Crypto test error"))
                .andExpect(jsonPath("$.timestamp").isNotEmpty());
    }

    @Test
    @DisplayName("GET /test-exceptions/email-taken should be handled by GlobalExceptionHandler and return 409")
    void whenEmailTakenException_thenReturns409() throws Exception {
        mockMvc.perform(get("/test-exceptions/email-taken")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value("EMAIL_IN_USE"))
                .andExpect(jsonPath("$.message").value("Email already in use: test@example.com"))
                .andExpect(jsonPath("$.timestamp").isNotEmpty());
    }

    @Test
    @DisplayName("GET /test-exceptions/generic should be handled by GlobalExceptionHandler and return 500")
    void whenGenericException_thenReturns500() throws Exception {
        mockMvc.perform(get("/test-exceptions/generic")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value("UNEXPECTED_ERROR"))
                .andExpect(jsonPath("$.message").value("Generic test error"))
                .andExpect(jsonPath("$.timestamp").isNotEmpty());
    }
}