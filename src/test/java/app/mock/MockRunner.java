package app.mock;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.intuit.karate.core.MockServer;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MockRunner {
    
    static MockServer server;
    
    @BeforeAll
    static void beforeAll() {
        server = MockServer
                .feature("classpath:app/mock/mock.feature")
                .pathPrefix("/api")
                .http(0).build(); 
    }

    @Test
    void testApi() {
        Results results = Runner.path("classpath:app/api/simple/simple.feature")
                .systemProperty("url.base", "http://localhost:" + server.getPort())
                .parallel(1);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }

}
