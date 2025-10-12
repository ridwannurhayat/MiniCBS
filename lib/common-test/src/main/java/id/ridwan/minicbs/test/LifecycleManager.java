package id.ridwan.minicbs.test;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Map;

public class LifecycleManager implements QuarkusTestResourceLifecycleManager {
    private PostgreSQLContainer<?> postgres;

    @Override
    public Map<String, String> start() {
        postgres = new PostgreSQLContainer<>("postgres:15")
                .withDatabaseName("minicbs_test")
                .withUsername("test")
                .withPassword("test");
        postgres.start();

        return Map.of(
                "test.database.url", postgres.getJdbcUrl()
        );
    }

    @Override
    public void stop() {
        if (postgres != null) {
            postgres.stop();
        }
    }
}
