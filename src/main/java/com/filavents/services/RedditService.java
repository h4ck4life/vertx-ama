package com.filavents.services;

import com.filavents.entity.Reddit;

import java.util.List;

public interface RedditService {
    Reddit getRandom();

    List<Reddit> getAllByAmaId(String amaId, int limit, int offset);

    List<Reddit> getAllByKeyword(String keyword, int limit, int offset);

    int getTotalByKeyword(String keyword);

    int countByAmaId(String amaId);
}
