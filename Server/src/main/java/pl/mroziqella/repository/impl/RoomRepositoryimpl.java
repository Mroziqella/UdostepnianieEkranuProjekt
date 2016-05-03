package pl.mroziqella.repository.impl;

import org.springframework.stereotype.Repository;
import pl.mroziqella.domain.Room;
import pl.mroziqella.domain.User;
import pl.mroziqella.repository.RoomRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Kamil on 03/05/2016.
 */
@Repository
public class RoomRepositoryimpl implements RoomRepository {
    @Override
    public boolean save(Room room) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(room);

        try{
            entityManager.getTransaction().commit();
        }catch (javax.persistence.RollbackException e){
            e.printStackTrace();
            return false;
        }

        entityManager.close();
        entityManagerFactory.close();



        return true;

    }
}
