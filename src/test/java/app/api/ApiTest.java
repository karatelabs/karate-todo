package app.api;

import app.App;
import io.karatelabs.core.Runner;
import io.karatelabs.core.SuiteResult;
import io.karatelabs.http.HttpServer;
import io.karatelabs.http.ServerConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiTest {

    static HttpServer server;

    @BeforeAll
    static void beforeAll() {
        ServerConfig config = App.serverConfig("src/main/java/app");
        server = App.start(config, 0);
    }

    @AfterAll
    static void afterAll() {
        if (server != null) {
            server.stopAndWait();
        }
    }

    @Test
    void testAll() {
        SuiteResult result = Runner.path("classpath:app/api")
                .tags("~@external")
                .systemProperty("serverUrl", "http://localhost:" + server.getPort())
                .parallel(1);
        assertEquals(0, result.getScenarioFailedCount(), String.join("\n", result.getErrors()));
    }

}
