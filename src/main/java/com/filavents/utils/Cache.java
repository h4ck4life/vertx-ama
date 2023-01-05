package com.filavents.utils;

import com.filavents.entity.Reddit;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class Cache {

    private static com.github.benmanes.caffeine.cache.Cache<Integer, Reddit> instance;

    private Cache() {
    }

    public static com.github.benmanes.caffeine.cache.Cache<Integer, Reddit> getInstance() {
        if (instance == null) {
            instance = Caffeine.newBuilder()
                    .expireAfterAccess(1, TimeUnit.DAYS)
                    .maximumSize(25000)
                    .build();
        }
        return instance;
    }

}
