package pl.btsoftware.ship;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Profile("it")
abstract class IntegrationTestConfiguration {
    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:12.6")
                .withDatabaseName("ships")
                .withUsername("ships")
                .withPassword("ships");

        public static Map<String, Object> getProperties() {
            Startables.deepStart(Stream.of(postgres)).join();

            return new HashMap<String, Object>() {{
                put("spring.datasource.url", postgres.getJdbcUrl());
                put("spring.datasource.username", postgres.getUsername());
                put("spring.datasource.password", postgres.getPassword());
            }};
        }

        @Override
        public void initialize(ConfigurableApplicationContext context) {
            ConfigurableEnvironment env = context.getEnvironment();
            env.getPropertySources().addFirst(new MapPropertySource("testcontainers", getProperties()));
        }
    }
}
