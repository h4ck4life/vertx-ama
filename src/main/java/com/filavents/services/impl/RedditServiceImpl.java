package com.filavents.services.impl;

import com.filavents.configs.Database;
import com.filavents.entity.Reddit;
import com.filavents.services.RedditService;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class RedditServiceImpl implements RedditService {

    static Logger logger = LoggerFactory.getLogger(RedditServiceImpl.class);

    @Override
    public Reddit getRandom() {
        Reddit reddit;
        EntityManager entityManager = Database.getEntityManagerFactory().createEntityManager();

        try {
            reddit = entityManager.createQuery(
                            "SELECT r FROM Reddit r ORDER BY RANDOM()",
                            Reddit.class
                    )
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
            reddit = null;
            logger.error(e.getMessage());
        } finally {
            entityManager.close();
        }

        return reddit;
    }

    @Override
    public List<Reddit> getAllByAmaId(String amaId, int limit, int offset) {
        List<Reddit> redditList = new ArrayList<>();
        EntityManager entityManager = Database.getEntityManagerFactory().createEntityManager();

        try {
            redditList = entityManager.createQuery(
                            "SELECT r FROM Reddit r WHERE r.amaId = :amaId",
                            Reddit.class
                    )
                    .setParameter("amaId", amaId)
                    .setMaxResults(limit)
                    .setFirstResult(offset)
                    .getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            entityManager.close();
        }

        return redditList;
    }

    @Override
    public List<Reddit> getAllByKeyword(String keyword, int limit, int offset) {
        List<Reddit> redditList = new ArrayList<>();
        EntityManager entityManager = Database.getEntityManagerFactory().createEntityManager();

        try {
            redditList = entityManager.createQuery(
                            "SELECT r FROM Reddit r WHERE r.title LIKE :keyword",
                            Reddit.class
                    )
                    .setParameter("keyword", "%" + keyword + "%")
                    .setMaxResults(limit)
                    .setFirstResult(offset)
                    .getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            entityManager.close();
        }

        return redditList;
    }

    @Override
    public int getTotalByKeyword(String keyword) {
        int total = 0;
        EntityManager entityManager = Database.getEntityManagerFactory().createEntityManager();

        try {
            total = entityManager.createQuery(
                            "SELECT COUNT(r) FROM Reddit r WHERE r.title LIKE :keyword",
                            Long.class
                    )
                    .setParameter("keyword", "%" + keyword + "%")
                    .getSingleResult()
                    .intValue();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            entityManager.close();
        }

        return total;
    }

    @Override
    public int getTotalByAmaId(String amaId) {
        int total = 0;
        EntityManager entityManager = Database.getEntityManagerFactory().createEntityManager();

        try {
            total = entityManager.createQuery(
                            "SELECT COUNT(r) FROM Reddit r WHERE r.amaId = :amaId",
                            Long.class
                    )
                    .setParameter("amaId", amaId)
                    .getSingleResult()
                    .intValue();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            entityManager.close();
        }

        return total;
    }
}
