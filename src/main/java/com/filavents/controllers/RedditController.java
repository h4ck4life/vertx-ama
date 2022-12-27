package com.filavents.controllers;

import com.filavents.services.RedditService;
import com.filavents.services.impl.RedditServiceImpl;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class RedditController {

    private static final RedditService redditService = new RedditServiceImpl();

    private RedditController() {
    }

    public static Future<JsonObject> getRandomAMA(RoutingContext ctx) {
        System.out.println(redditService.getRandom());
        JsonObject resp = new JsonObject().put("hello", "world");
        return Future.succeededFuture(resp);
    }
}
