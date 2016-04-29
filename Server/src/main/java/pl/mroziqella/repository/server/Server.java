package pl.mroziqella.repository.server;


import org.springframework.stereotype.Service;
import pl.mroziqella.inte.Image;
import pl.mroziqella.domain.User;
import pl.mroziqella.exception.ImageNotFound;
import pl.mroziqella.inte.MouseInfo;
import pl.mroziqella.inte.SharingPicture;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Kamil
 */

public class Server extends UnicastRemoteObject implements SharingPicture{

    public static Map<String, Image> imageData = new HashMap<String, Image>();
    public static Map<String, MouseInfo> mouseData = new HashMap<String, MouseInfo>();
    private Map<String, User> users = new HashMap<>();

    public Server() throws RemoteException {
        super();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            Server server = new Server();
            server.start(2000, "server");
        } catch (RemoteException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start(int port, String serverName) {
        try {
            Registry reg = LocateRegistry.createRegistry(port);
            reg.rebind(serverName, new Server());
            System.out.println("Server start");
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Override
    public String getTest() throws RemoteException {
        return "Test";
    }

    @Override
    public MouseInfo getMouseClick(String login) throws RemoteException {
        return mouseData.get(login);
    }

    @Override
    public void setMouseClick(String login, MouseInfo mouseInfo) throws RemoteException {
        mouseData.put(login,mouseInfo);
    }

    @Override
    public void writeImageToServer(Image image, String login) throws RemoteException {
        byte[] imageBase64=Base64.getEncoder().encode(image.getImage());
        image.setImageBase64(imageBase64);
        image.setTime(Calendar.getInstance());
        imageData.put(login, image);
    }

    @Override
    public Image readImageFromServer(String login) throws RemoteException {
        try {
            return imageData.get(login);
        } catch (NullPointerException e) {
            throw new ImageNotFound();
        }
    }

    @Override
    public byte[] readImageFromServerBase64(String login) throws RemoteException {
        try {
            return imageData.get(login).getImageBase64();
        } catch (NullPointerException e) {
            throw new ImageNotFound();
        }
    }

    @Override
    public boolean isUser(String login, String password) throws RemoteException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();


        User user = (User) entityManager.find(User.class, login);
        try {
            entityManager.getTransaction().commit();
        } catch (javax.persistence.RollbackException e) {
            return false;
        }

        entityManager.close();
        entityManagerFactory.close();
        if (user != null && user.getPassword().equals(password)) {
            imageData.put(login, null);
            mouseData.put(login,new MouseInfo());
            return true;
        }

        return false;
    }

}
