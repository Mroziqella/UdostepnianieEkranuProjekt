/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.mroziqella.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Kamil
 */
public class ConnectedTest{

    @Test
    public void connectTest() {




        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        User user  = new User();
        user.setLogin("Adam3");
        user.seteMail("safsadf@o2.pl");
        user.setPassword("saf");

        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
        Assert.assertEquals("Adam3",user.getLogin());
    }

}
