package test;

import app.App;
import com.intuit.karate.http.HttpServer;
import com.intuit.karate.http.ServerConfig;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LocalRunner {

    private static final Logger logger = LoggerFactory.getLogger(LocalRunner.class);

    @Test
    void testServer() {
        ServerConfig config = App.serverConfig("src/main/java/app");
        HttpServer.config(config).http(8080).build().waitSync();
    }        

}
