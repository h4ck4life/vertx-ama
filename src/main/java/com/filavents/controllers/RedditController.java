package com.filavents.controllers;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class RedditController {

    private RedditController() {
    }

    public static Future<JsonObject> getRandomAMA(RoutingContext ctx) {
        JsonObject resp = new JsonObject().put("hello", "world");
        return Future.succeededFuture(resp);
    }
}
