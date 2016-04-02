package pl.mroziqella.repository.server;


import org.springframework.stereotype.Service;
import pl.mroziqella.domain.Image;
import pl.mroziqella.inte.SharingPicture;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Kamil
 */
@Service
public class Server extends UnicastRemoteObject implements SharingPicture {
    private static Map<String,Image> imageData = new HashMap<String, Image>();

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

    public static Map<String, Image> getImageData() {
        return imageData;
    }

    @Override
    public String getTest() throws RemoteException {
        return "Test";
    }

    @Override
    public void writeImageToServer(byte[] image, String login) throws RemoteException {
        if(imageData.containsKey(login)) {
            imageData.put(login, new Image(Calendar.getInstance(), image));
        }
    }

    @Override
    public byte[] readImageFromServer(String login) throws RemoteException {
        return imageData.get(login).getImage();
    }

    @Override
    public boolean loginUser(String login) throws RemoteException {
        imageData.put(login,null);
        return true;
    }
}
