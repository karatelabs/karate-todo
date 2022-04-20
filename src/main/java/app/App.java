package app;

import com.intuit.karate.http.ServerConfig;
import com.intuit.karate.http.ServerContext;

public class App {

    public static ServerConfig serverConfig(String root) {
        ServerConfig config = new ServerConfig(root)
                .useGlobalSession(true)
                .autoCreateSession(true);
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

}
