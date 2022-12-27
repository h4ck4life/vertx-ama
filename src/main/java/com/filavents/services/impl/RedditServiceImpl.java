package com.filavents.services.impl;

import com.filavents.configs.Database;
import com.filavents.entity.Reddit;
import com.filavents.services.RedditService;
import jakarta.persistence.EntityManager;

public class RedditServiceImpl implements RedditService {
    @Override
    public Reddit getRandom() {
        EntityManager entityManager = Database.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        // get first result in json format
        Reddit reddit = entityManager.createQuery("SELECT r FROM Reddit r ORDER BY RANDOM()", Reddit.class).setMaxResults(1).getSingleResult();
        entityManager.close();

        return reddit;
    }
}
