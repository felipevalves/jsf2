package br.com.felipe.gnre.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class BaseDao {

    BaseDao() {
    }

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistencia");

    protected static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

}
