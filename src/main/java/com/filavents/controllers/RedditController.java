package com.filavents.controllers;

import com.filavents.dto.RedditListResponse;
import com.filavents.entity.Reddit;
import com.filavents.services.RedditService;
import com.filavents.services.impl.RedditServiceImpl;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.ext.web.RoutingContext;

import java.util.*;

public class RedditController {

    private static final RedditService redditService = new RedditServiceImpl();
    private static final int LIMIT = 10;

    private RedditController() {
    }

    public static Future<Map<String, Object>> getRandomAMA(RoutingContext ctx) {
        return Future.future(promise -> {
            Reddit reddit = redditService.getRandom();
            Map<String, Object> response = new HashMap<>();
            if (reddit != null) {
                int total = redditService.getTotalByAmaId(reddit.getAmaId());
                response.put("total", total);
                response.put("data", reddit);
                promise.complete(response);
            } else {
                response.put("total", 0);
                response.put("data", null);
                promise.complete(response);
            }
        });
    }

    public static Future<Map<String, Object>> getAMAById(RoutingContext ctx) {
        return Future.future(returnPromise -> {
            String amaId = ctx.pathParam("amaId");
            List<Future> futureList = new ArrayList<>();

            // Get AMA by amaId
            futureList.add(Future.future(promise -> {
                List<Reddit> redditList = redditService.getAllByAmaId(amaId, LIMIT, getOffset(ctx));
                if (redditList.size() > 0) {
                    promise.complete(redditList);
                } else {
                    promise.complete(Collections.emptyList());
                }
            }));

            // Count total
            futureList.add(Future.future(promise -> {
                int total = redditService.getTotalByAmaId(amaId);
                promise.complete(total);
            }));

            // Merge all futures
            CompositeFuture.all(futureList).onComplete(ar -> {
                if (ar.succeeded()) {
                    List<Reddit> redditList = ar.result().resultAt(0);
                    int total = ar.result().resultAt(1);
                    RedditListResponse redditListResponse = new RedditListResponse(total, redditList);
                    returnPromise.complete(redditListResponse.toMap());
                } else {
                    RedditListResponse redditListResponse = new RedditListResponse(0, Collections.emptyList());
                    returnPromise.complete(redditListResponse.toMap());
                }
            });
        });
    }

    public static Future<Map<String, Object>> getAMAByKeyword(RoutingContext ctx) {
        return Future.future(returnPromise -> {
            String keyword = ctx.pathParam("keyword");
            List<Future> futureList = new ArrayList<>();

            // Get keyword search result
            futureList.add(Future.future(promise -> {
                List<Reddit> redditList = redditService.getAllByKeyword(keyword, LIMIT, getOffset(ctx));
                if (redditList.size() > 0) {
                    promise.complete(redditList);
                } else {
                    promise.complete(Collections.emptyList());
                }
            }));

            // Get total count
            futureList.add(Future.future(promise -> {
                int redditListCount = redditService.getTotalByKeyword(keyword);
                promise.complete(redditListCount);
            }));

            // Combine both result
            CompositeFuture.all(futureList).onComplete(ar -> {
                if (ar.succeeded()) {
                    RedditListResponse redditListResponse = new RedditListResponse(
                            (int) ar.result().list().get(1),
                            (List<Reddit>) ar.result().list().get(0)
                    );
                    returnPromise.complete(redditListResponse.toMap());
                } else {
                    returnPromise.complete(new RedditListResponse(0, Collections.emptyList()).toMap());
                }
            });
        });
    }

    private static int getOffset(RoutingContext ctx) {
        int page = ctx.pathParam("page") == null ? 1 : Integer.parseInt(ctx.pathParam("page"));
        return (page - 1) * LIMIT;
    }

}
