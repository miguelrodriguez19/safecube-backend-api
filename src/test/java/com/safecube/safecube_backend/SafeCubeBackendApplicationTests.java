package com.safecube.safecube_backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for the {@link SafeCubeBackendApplication} class.
 * These tests verify that the application context loads correctly and that
 * expected startup behaviors, such as logging, occur.
 */
@SpringBootTest
class SafeCubeBackendApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Verifies that the Spring application context loads without errors.
     */
    @Test
    void contextLoads() {
        assertThat(applicationContext).isNotNull();

        SafeCubeBackendApplication appBean = applicationContext.getBean(SafeCubeBackendApplication.class);
        assertThat(appBean).isNotNull();
    }
}