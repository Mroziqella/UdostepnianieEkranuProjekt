package pl.mroziqella.repository.impl;

import org.springframework.stereotype.Repository;
import pl.mroziqella.domain.Room;
import pl.mroziqella.domain.User;
import pl.mroziqella.repository.RoomRepository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public ArrayList<Room> getAllRoomsFromUser(String userName) {
        List<Room> results;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();


        String hql = "SELECT e FROM Room e";
        TypedQuery<Room> query = entityManager.createQuery(hql,Room.class);
        results = query.getResultList();

        try{
            entityManager.getTransaction().commit();
        }catch (javax.persistence.RollbackException e){
            e.printStackTrace();
            return null;
        }

        entityManager.close();
        entityManagerFactory.close();

        return new ArrayList<Room>(results);
    }
}
