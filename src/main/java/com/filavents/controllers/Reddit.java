package com.filavents.controllers;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class Reddit {

    private Reddit() {
    }

    public static Future<JsonObject> getRandomAMA(RoutingContext ctx) {
        return Future.succeededFuture(new JsonObject().put("hello", "world"));
    }
}
