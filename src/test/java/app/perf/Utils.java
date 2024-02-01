package app.perf;

import com.intuit.karate.http.HttpServer;
import com.intuit.karate.http.ServerConfig;

public class Utils {

    public static void startServer() {
        if ("true".equals(System.getenv("START_SERVER"))) {
            System.out.println("*** env property START_SERVER was 'true', will start server ...");
            ServerConfig config = app.App.serverConfig("src/main/java/app");
            HttpServer.config(config).http(8080).build();
        }
    }

}
