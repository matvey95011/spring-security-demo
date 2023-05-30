package ru.example.java.spring.demo.config;

import lombok.SneakyThrows;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;

@ContextConfiguration(initializers = AbstractTest.Initializer.class)
public abstract class AbstractTest extends ConfiguratorTestHelper {

    private static final PostgreSQLContainer dbContainer = new PostgreSQLContainer("postgres:10.7");

    private static final String NETWORK_ALIAS = "postgres";

    static {
        dbContainer.withNetwork(Network.SHARED).withNetworkAliases(NETWORK_ALIAS).start();
    }

    private static final String JDBC_URL = dbContainer.getJdbcUrl();
    private static final String USERNAME = dbContainer.getUsername();
    private static final String PASSWORD = dbContainer.getPassword();
    private static final String CLEAR_COMMAND = String.format(
            "pg_dump --schema-only --format c -U %s | " + "pg_restore --schema-only --clean -U %s -d %s",
            USERNAME,
            USERNAME,
            USERNAME);

    @SneakyThrows
    protected void clearDb() {
        dbContainer.execInContainer("bash", "-c", CLEAR_COMMAND);
    }

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues
                    .of("spring.datasource.url=" + JDBC_URL,
                            "spring.datasource.username=" + USERNAME,
                            "spring.datasource.password=" + PASSWORD)
                    .applyTo(configurableApplicationContext.getEnvironment());
        }
    }

}
