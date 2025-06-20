package com.safecube.safecube_backend;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * <h2>Smoke / integration test for {@link SafeCubeBackendApplication}</h2>
 *
 * <p>This is the <em>most lightweight</em> Spring-Boot integration test possible:
 * it boots the whole application context-using the default profile-and asserts that:
 * </p>
 * <ul>
 *   <li>The {@link ApplicationContext} itself is instantiated.</li>
 *   <li>The main Spring Boot application class ({@link SafeCubeBackendApplication}) is registered as a bean.</li>
 * </ul>
 *
 * <p>No web server is started and no external resources are touched; nevertheless the test
 * exercises the <strong>complete auto-configuration graph</strong>.  It serves as a
 * <em>“canary”</em> to detect mis-configured beans, missing properties, or circular
 * dependencies early in the build pipeline.</p>
 *
 * @author miguelrodriguez19
 * @since 0.2.0
 */
@SpringBootTest
class SafeCubeBackendApplicationIT {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        // Application context is up
        assertThat(applicationContext).isNotNull();

        // Main application bean is present
        SafeCubeBackendApplication appBean =
                applicationContext.getBean(SafeCubeBackendApplication.class);
        assertThat(appBean).isNotNull();
    }
}
