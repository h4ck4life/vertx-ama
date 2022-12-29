package com.filavents;

import com.filavents.configs.Database;
import com.filavents.controllers.RedditController;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class App {

    static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        // Init the EMF
        Database.getEntityManagerFactory();

        // Vertx initialize
        Vertx vertx = Vertx.vertx();

        // Init HTTP server
        HttpServerOptions serverOptions = new HttpServerOptions();
        serverOptions.setCompressionSupported(true);
        HttpServer server = vertx.createHttpServer(serverOptions);

        // Init Router
        Router router = Router.router(vertx);

        // Middleware code
        router.route().handler(ctx -> {
            ctx.response().putHeader("x-powered-by", "vertx");
            ctx.response().putHeader("Access-Control-Allow-Origin", "*");
            ctx.next();
        });

        // Routers
        router.get("/api/random").respond(RedditController::getRandomAMA);
        router.get("/api/:amaId").respond(RedditController::getAMAById);
        router.get("/api/:amaId/page/:page").respond(RedditController::getAMAById);
        router.get("/api/search/:keyword").respond(RedditController::getAMAByKeyword);
        router.get("/api/search/:keyword/page/:page").respond(RedditController::getAMAByKeyword);

        // setindexpage return index.html page
        router.get("/").handler(ctx -> {
            ctx.response().sendFile("web/reddit-ama-web/dist/reddit-ama-web/index.html");
        });

        // Set static web root
        router.route("/*").handler(StaticHandler.create().setWebRoot("web/reddit-ama-web/dist/reddit-ama-web"));

        // Start the server
        int runningPort = Integer.parseInt(System.getenv("PORT"));
        server.requestHandler(router).listen(runningPort).andThen(httpServerAsyncResult -> {
            if (httpServerAsyncResult.succeeded()) {
                logger.info("Server started on port: " + runningPort);
            }
        });

    }
}
