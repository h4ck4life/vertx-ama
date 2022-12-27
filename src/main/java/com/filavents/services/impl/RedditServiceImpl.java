package com.filavents.services.impl;

import com.filavents.configs.Database;
import com.filavents.entity.Reddit;
import com.filavents.services.RedditService;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import jakarta.persistence.EntityManager;

public class RedditServiceImpl implements RedditService {

    static Logger logger = LoggerFactory.getLogger(RedditServiceImpl.class);

    @Override
    public Reddit getRandom() {
        Reddit reddit;
        EntityManager entityManager = Database.getEntityManagerFactory().createEntityManager();

        try {
            reddit = entityManager.createQuery("SELECT r FROM Reddit r ORDER BY RANDOM()", Reddit.class).setMaxResults(1).getSingleResult();
        } catch (Exception e) {
            reddit = null;
            logger.error(e.getMessage());
        } finally {
            entityManager.close();
        }

        return reddit;
    }
}
