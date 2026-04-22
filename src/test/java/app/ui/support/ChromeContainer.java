package app.ui.support;

import io.karatelabs.driver.cdp.CdpDriver;
import io.karatelabs.driver.cdp.CdpDriverOptions;
import net.minidev.json.JSONValue;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;

public class ChromeContainer extends GenericContainer<ChromeContainer> {

    private static final String IMAGE = "chromedp/headless-shell:latest";
    private static final int CDP_PORT = 9222;

    public ChromeContainer() {
        super(DockerImageName.parse(IMAGE));
        withExposedPorts(CDP_PORT);
        withCommand("--remote-allow-origins=*");
        waitingFor(Wait.forHttp("/json/version").forPort(CDP_PORT));
        withStartupTimeout(Duration.ofMinutes(2));
        // Native Linux docker (GitHub Actions): host.docker.internal needs explicit mapping.
        withExtraHost("host.docker.internal", "host-gateway");
    }

    public String getCdpUrl() {
        String host = getHost();
        int port = getMappedPort(CDP_PORT);
        return fetchWebSocketUrl(host, port);
    }

    public CdpDriver createDriver(CdpDriverOptions options) {
        return CdpDriver.connect(getCdpUrl(), options);
    }

    public String getHostAccessUrl(int hostPort) {
        // host.docker.internal works natively on Docker Desktop (macOS/Windows)
        // and on Linux via the `host-gateway` extra-host we configure above.
        return "http://host.docker.internal:" + hostPort;
    }

    @SuppressWarnings("unchecked")
    private String fetchWebSocketUrl(String host, int port) {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://" + host + ":" + port + "/json/new?about:blank"))
                    .timeout(Duration.ofSeconds(10))
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                Map<String, Object> newTarget = (Map<String, Object>) JSONValue.parse(response.body());
                if (newTarget != null) {
                    String wsUrl = (String) newTarget.get("webSocketDebuggerUrl");
                    if (wsUrl != null) {
                        return wsUrl.replace("localhost", host).replace("127.0.0.1", host);
                    }
                }
            }
            throw new RuntimeException("Failed to create new tab in Chrome container");
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch WebSocket URL: " + e.getMessage(), e);
        }
    }

}
