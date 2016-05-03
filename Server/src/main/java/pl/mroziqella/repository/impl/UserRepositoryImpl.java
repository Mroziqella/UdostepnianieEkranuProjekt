package pl.mroziqella.repository.impl;


import org.springframework.stereotype.Repository;
import pl.mroziqella.domain.User;
import pl.mroziqella.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

/**
 * Created by Kamil on 25/03/2016.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {






    @Override
    public boolean save(User user) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(user);

        try{
        entityManager.getTransaction().commit();
        }catch (javax.persistence.RollbackException e){
            return false;
        }

        entityManager.close();
        entityManagerFactory.close();



        return true;

    }
    @Override
    public User getUser(String login){
        User user;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();


        user = (User) entityManager.find(User.class, login);
        try {
            entityManager.getTransaction().commit();
        } catch (javax.persistence.RollbackException e) {
            return null;
        }

        entityManager.close();
        entityManagerFactory.close();
        return user;

    }

}
