package com.filavents.services.impl;

import com.filavents.configs.Database;
import com.filavents.entity.Reddit;
import com.filavents.services.RedditService;
import jakarta.persistence.EntityManager;

public class RedditServiceImpl implements RedditService {
    @Override
    public Reddit getRandom() {
        Reddit reddit;
        EntityManager entityManager = Database.getEntityManagerFactory().createEntityManager();

        try {
            reddit = entityManager.createQuery("SELECT r FROM Reddit r ORDER BY RANDOM()", Reddit.class).setMaxResults(1).getSingleResult();
        } finally {
            entityManager.close();
        }

        return reddit;
    }
}
