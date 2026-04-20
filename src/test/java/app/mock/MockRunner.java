package app.mock;

import io.karatelabs.core.MockServer;
import io.karatelabs.core.Runner;
import io.karatelabs.core.SuiteResult;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MockRunner {

    static MockServer server;

    @BeforeAll
    static void beforeAll() {
        server = MockServer
                .feature("classpath:app/mock/mock.feature")
                .pathPrefix("/api")
                .start();
    }

    @AfterAll
    static void afterAll() {
        if (server != null) {
            server.stopAndWait();
        }
    }

    @Test
    void testApi() {
        SuiteResult result = Runner.path("classpath:app/api/simple/simple.feature")
                .systemProperty("url.base", "http://localhost:" + server.getPort())
                .parallel(1);
        assertEquals(0, result.getScenarioFailedCount(), String.join("\n", result.getErrors()));
    }

}
