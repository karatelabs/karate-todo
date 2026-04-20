package app;

import io.karatelabs.http.*;
import io.karatelabs.markup.RootResourceResolver;

import java.util.HashMap;
import java.util.function.Function;

public class App {

    final static Session session = new Session("singleton", new HashMap<>(), System.currentTimeMillis(), System.currentTimeMillis(), -1);

    public static ServerConfig serverConfig(String root) {
        // v2's ServerRequestHandler routes /api/* to JS files and /pub/* to static
        // resources out of the box — no contextFactory needed. CSRF is on by
        // default in v2; the todo demo is a simple API backend hit directly
        // by tests, so disable it. Sessions are only "enabled" when a
        // SessionStore is set — needed so `context.init()` in the JS handlers
        // can create a session and stash the todo list.
        return new ServerConfig(root)
                .csrfEnabled(false)
                .sessionStore(new SessionStore() {
                    @Override
                    public Session create(int i) {
                        return session;
                    }

                    @Override
                    public Session get(String s) {
                        return session;
                    }

                    @Override
                    public void save(Session session) {

                    }

                    @Override
                    public void delete(String s) {

                    }
                });
    }

    public static Function<io.karatelabs.http.HttpRequest, io.karatelabs.http.HttpResponse> handler(ServerConfig config) {
        return new ServerRequestHandler(config, new RootResourceResolver(config.getResourceRoot()));
    }

    public static HttpServer start(ServerConfig config, int port) {
        return HttpServer.start(port, handler(config));
    }

    public static void main(String[] args) {
        ServerConfig config = serverConfig(args[0]);
        start(config, 8080).waitSync();
    }

}
