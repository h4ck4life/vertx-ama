package com.filavents.services;

import com.filavents.services.impl.RedditServiceImpl;
import com.google.inject.ImplementedBy;

@ImplementedBy(RedditServiceImpl.class)
public interface RedditService {
    String getRandom();
}
