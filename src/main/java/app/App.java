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
        Function<io.karatelabs.http.HttpRequest, io.karatelabs.http.HttpResponse> inner =
                new ServerRequestHandler(config, new RootResourceResolver(config.getResourceRoot()));
        // Serialize requests across worker threads. The JS handlers in src/main/java/app/api
        // mutate session.todos (push/splice) on a shared singleton session — and JS array
        // operations (push, splice, ...) are non-atomic read-then-write sequences in the
        // engine, so concurrent requests silently lose items or surface 500s from
        // IndexOutOfBoundsException / ConcurrentModificationException inside the engine's
        // length-update path. A whole-request lock is the simplest fix; karate-feature
        // mocks already do this internally. Fine for an in-memory demo app — a real
        // multi-tenant service would use per-resource or per-session locking instead.
        java.util.concurrent.locks.ReentrantLock lock = new java.util.concurrent.locks.ReentrantLock();
        return req -> {
            lock.lock();
            try {
                return inner.apply(req);
            } finally {
                lock.unlock();
            }
        };
    }

    public static HttpServer start(ServerConfig config, int port) {
        return HttpServer.start(port, handler(config));
    }

    public static void main(String[] args) {
        ServerConfig config = serverConfig(args[0]);
        start(config, 8080).waitSync();
    }

}
