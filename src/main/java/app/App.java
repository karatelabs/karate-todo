package app;

import com.intuit.karate.http.HttpServer;
import com.intuit.karate.http.ServerConfig;
import com.intuit.karate.http.ServerContext;

public class App {

    public static ServerConfig serverConfig(String root) {
        ServerConfig config = new ServerConfig(root).useGlobalSession(true);
        config.contextFactory(request -> {
            ServerContext context = new ServerContext(config, request);
            String path = request.getPath();
            if (context.setApiIfPathStartsWith("/api/")) {
                context.setLockNeeded(true);
            } else if (path.endsWith(".ico") || path.startsWith("/pub/")) {
                context.setHttpGetAllowed(true);
            }
            return context;
        });
        return config;
    }
    
    public static void main(String[] args) {
        ServerConfig config = App.serverConfig(args[0]);
        HttpServer.config(config).local(false).http(8080).build().waitSync();        
    }

}
