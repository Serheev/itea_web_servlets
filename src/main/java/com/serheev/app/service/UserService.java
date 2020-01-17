package com.serheev.app.service;

import com.serheev.app.dao.DaoInterface;
import com.serheev.app.entities.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserService implements DaoInterface<UserEntity> {
    private static EntityManager em = Persistence.createEntityManagerFactory("UserPersistence").createEntityManager();

    @Override
    public Optional<UserEntity> get(long id) {
        return Optional.ofNullable(em.find(UserEntity.class, id));
    }

    @Override
    public List getAll() {
        TypedQuery<UserEntity> namedQuery = em.createNamedQuery("UserEntity.getAll", UserEntity.class);
        return namedQuery.getResultList();
    }

    @Override
    public UserEntity save(UserEntity user) {
        em.getTransaction().begin();
        UserEntity userFromDB = em.merge(user);
        em.getTransaction().commit();
        return userFromDB;
    }

    @Override
    public void update(UserEntity user, String[] params) {
        user.setName(Objects.requireNonNull(params[0], "Name cannot be null"));
        user.setPassword(params[1]);
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    @Override
    public void delete(long id) {
        em.getTransaction().begin();
        em.remove(get(id).get());
        em.getTransaction().commit();
    }
}
