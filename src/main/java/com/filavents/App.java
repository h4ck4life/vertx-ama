package com.filavents;

import com.filavents.controllers.Reddit;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class App {

    static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);

        // Middleware code
        router.route().handler(ctx -> {
            ctx.response().putHeader("x-server", "vert.x");
            ctx.next();
        });

        router.get("/hello").respond(Reddit::getRandomAMA);

        int runningPort = Integer.parseInt(System.getenv("PORT"));
        server.requestHandler(router).listen(runningPort).andThen(httpServerAsyncResult -> {
            if (httpServerAsyncResult.succeeded()) {
                logger.info("Server started on port: " + runningPort);
            }
        });

    }
}
