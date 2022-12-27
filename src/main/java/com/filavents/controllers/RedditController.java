package com.filavents.controllers;

import com.filavents.entity.Reddit;
import com.filavents.services.RedditService;
import com.filavents.services.impl.RedditServiceImpl;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class RedditController {

    private static final RedditService redditService = new RedditServiceImpl();

    private RedditController() {
    }

    public static Future<Reddit> getRandomAMA(RoutingContext ctx) {
        Reddit reddit = redditService.getRandom();
        return Future.succeededFuture(reddit);
    }
}
