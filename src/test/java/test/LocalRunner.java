package test;

import app.App;
import io.karatelabs.http.ServerConfig;
import org.junit.jupiter.api.Test;

class LocalRunner {

    @Test
    void testServer() {
        ServerConfig config = App.serverConfig("src/main/java/app").devMode(true);
        App.start(config, 8080).waitSync();
    }

}
