package com.filavents.services;

import com.filavents.entity.Reddit;

import java.util.List;

public interface RedditService {
    Reddit getRandom();
    List<Reddit> getAllByAmaId(String amaId);
}
