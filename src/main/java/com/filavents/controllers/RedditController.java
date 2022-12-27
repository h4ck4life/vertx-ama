package com.filavents.controllers;

import com.filavents.entity.Reddit;
import com.filavents.services.RedditService;
import com.filavents.services.impl.RedditServiceImpl;
import io.vertx.core.Future;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

public class RedditController {

    private static final RedditService redditService = new RedditServiceImpl();

    private RedditController() {
    }

    public static Future<Reddit> getRandomAMA(RoutingContext ctx) {
        Reddit reddit = redditService.getRandom();
        if (reddit == null) {
            ctx.response().setStatusCode(404);
            return Future.failedFuture("No AMA found");
        }
        return Future.succeededFuture(reddit);
    }

    public static Future<List<Reddit>> getAMAById(RoutingContext ctx) {
        String amaId = ctx.request().getParam("amaId");
        List<Reddit> reddit = redditService.getAllByAmaId(amaId);
        if (reddit.size() < 1) {
            ctx.response().setStatusCode(404);
            return Future.failedFuture("No AMA found");
        }
        return Future.succeededFuture(reddit);
    }
}
