package id.ridwan.minicbs.test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@QuarkusTestResource(LifecycleManager.class)
public abstract class AbstractIntegrationTest {
}
