package com.filavents;

import com.filavents.configs.Database;
import com.filavents.controllers.RedditController;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
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
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);

        // Middleware code
        router.route().handler(ctx -> {
            ctx.response().putHeader("x-powered-by", "vertx");
            ctx.response().putHeader("Access-Control-Allow-Origin", "*");
            ctx.next();
        });

        // setindexpage return index.html page
        router.get("/").handler(ctx -> {
            ctx.response().sendFile("web/reddit-ama-web/dist/reddit-ama-web/index.html");
        });

        // Routers
        router.get("/api/random").respond(RedditController::getRandomAMA);
        router.get("/api/id/:amaId").respond(RedditController::getAMAById);
        router.get("/api/id/:amaId/page/:page").respond(RedditController::getAMAById);
        router.get("/api/search/:keyword").respond(RedditController::getAMAByKeyword);
        router.get("/api/search/:keyword/page/:page").respond(RedditController::getAMAByKeyword);

        // setWebRoot
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
