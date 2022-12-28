package com.filavents.controllers;

import com.filavents.entity.Reddit;
import com.filavents.services.RedditService;
import com.filavents.services.impl.RedditServiceImpl;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.Collections;
import java.util.List;

public class RedditController {

    private static final RedditService redditService = new RedditServiceImpl();
    private static final int LIMIT = 10;

    private RedditController() {
    }

    public static Future<Reddit> getRandomAMA(RoutingContext ctx) {
        return Future.future(promise -> {
            Reddit reddit = redditService.getRandom();
            if (reddit != null) {
                promise.complete(reddit);
            } else {
                promise.complete(new JsonObject().put("error", "No AMA found").mapTo(Reddit.class));
            }
        });
    }

    public static Future<List<Reddit>> getAMAById(RoutingContext ctx) {
        return Future.future(promise -> {
            String amaId = ctx.pathParam("amaId");
            List<Reddit> redditList = redditService.getAllByAmaId(amaId, LIMIT, getOffset(ctx));
            if (redditList.size() > 0) {
                promise.complete(redditList);
            } else {
                promise.complete(Collections.emptyList());
            }
        });
    }

    public static Future<List<Reddit>> getAMAByKeyword(RoutingContext ctx) {
        return Future.future(promise -> {
            String keyword = ctx.pathParam("keyword");
            List<Reddit> redditList = redditService.getAllByKeyword(keyword, LIMIT, getOffset(ctx));
            if (redditList.size() > 0) {
                promise.complete(redditList);
            } else {
                promise.complete(Collections.emptyList());
            }
        });
    }

    private static int getOffset(RoutingContext ctx) {
        int page = ctx.pathParam("page") == null ? 1 : Integer.parseInt(ctx.pathParam("page"));
        return (page - 1) * LIMIT;
    }

}
