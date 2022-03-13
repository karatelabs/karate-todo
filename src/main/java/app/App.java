package app;

import com.intuit.karate.http.ServerConfig;
import com.intuit.karate.http.ServerContext;

/**
 *
 * @author peter
 */
public class App {

    public static ServerConfig serverConfig(String root) {
        ServerConfig config = new ServerConfig(root);
        config.contextFactory(request -> {
            ServerContext context = new ServerContext(config, request);
            String path = request.getPath();
            if (path.startsWith("/api/")) {
                context.setApi(true);
            } else if (path.endsWith(".ico") || path.startsWith("/pub/")) {
                context.setHttpGetAllowed(true);
            }
            return context;
        });
        return config;
    }

}
