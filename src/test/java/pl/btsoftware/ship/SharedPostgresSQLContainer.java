package pl.btsoftware.ship;

import org.testcontainers.containers.PostgreSQLContainer;

public class SharedPostgresSQLContainer extends PostgreSQLContainer<SharedPostgresSQLContainer> {

    private static final String IMAGE_VERSION = "postgres:12.6";
    private static SharedPostgresSQLContainer container;

    private SharedPostgresSQLContainer() {
        super(IMAGE_VERSION);
    }

    public static SharedPostgresSQLContainer getInstance() {
        if (container == null) {
            container = new SharedPostgresSQLContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}
