package com.filavents.dto;

import com.filavents.entity.Reddit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedditListResponse {
    private final int total;

    private final List<Reddit> redditList;

    // constructor
    public RedditListResponse(int total, List<Reddit> redditList) {
        this.total = total;
        this.redditList = redditList;
    }

    public int getTotal() {
        return total;
    }

    public List<Reddit> getRedditList() {
        return redditList;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("data", redditList);
        return map;
    }

}
