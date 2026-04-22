package app.ui;

import app.App;
import app.ui.support.ChromeContainer;
import app.ui.support.ContainerDriverProvider;
import io.karatelabs.core.Runner;
import io.karatelabs.core.SuiteResult;
import io.karatelabs.http.HttpServer;
import io.karatelabs.http.ServerConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {

    // Fixed port: Testcontainers.exposeHostPorts(...) must run before the app starts,
    // so we can't pick a random port after the server binds.
    private static final int PORT = 18080;

    static {
        // Docker 29.x API version negotiation workaround.
        // https://github.com/testcontainers/testcontainers-java/issues/11212
        System.setProperty("api.version", "1.44");
    }

    static HttpServer server;
    static ChromeContainer chrome;

    @BeforeAll
    static void beforeAll() {
        ServerConfig config = App.serverConfig("src/main/java/app");
        server = App.start(config, PORT);
        chrome = new ChromeContainer();
        chrome.start();
    }

    @AfterAll
    static void afterAll() {
        if (chrome != null) {
            chrome.stop();
        }
        if (server != null) {
            server.stopAndWait();
        }
    }

    @Test
    void testAll() {
        ContainerDriverProvider provider = new ContainerDriverProvider(chrome);
        // One hybrid suite: api + ui features in a single report. Same in-process
        // app serves both — karate HTTP hits localhost, the browser (in the
        // container) hits host.docker.internal.
        SuiteResult result = Runner.path("classpath:app/api", "classpath:app/ui")
                // @external: features that hit external hosts (google, httpbin, ...).
                // @todo: features gated on a karate-js 2.0.5 fix (see TODOs).
                .tags("~@external", "~@todo")
                .systemProperty("serverUrl", chrome.getHostAccessUrl(PORT))
                .systemProperty("apiUrl", "http://localhost:" + PORT)
                .driverProvider(provider)
                .parallel(1);
        assertEquals(0, result.getScenarioFailedCount(), String.join("\n", result.getErrors()));
    }

}
