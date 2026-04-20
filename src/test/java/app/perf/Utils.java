package app.perf;

import app.App;
import io.karatelabs.http.ServerConfig;

public class Utils {

    public static void startServer() {
        if ("true".equals(System.getenv("START_SERVER"))) {
            System.out.println("*** env property START_SERVER was 'true', will start server ...");
            ServerConfig config = App.serverConfig("src/main/java/app");
            App.start(config, 8080);
        }
    }

}
