package pl.mroziqella.service;

import pl.mroziqella.inte.SharingPicture;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Kamil on 21/03/2016.
 */
public class Client {

    private String address = null, nameServer = null;
    private int port = 0;
    private SharingPicture rmi;

    public Client(String address, String nameServer, int port) {
        this.address = address;
        this.nameServer = nameServer;
        this.port = port;
    }

    /**
     * @param args
     */

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", "server", 2000);
        String test;
        try {
            client.connect();
            test = client.getRmi().getTest();
            if (test.equals("Test"))
                System.out.println("TEST: Completed");
            else
                System.out.println("TEST: Failed");


        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @throws RemoteException
     * @throws NotBoundException
     */
    public void connect() throws RemoteException, NotBoundException {
        Registry reg = LocateRegistry.getRegistry(address, port);
        rmi = (SharingPicture) reg.lookup(nameServer);
        System.out.println("Conencted");
    }


    /**
     * Daje dostep do metod udostepnionych przez RMI
     *
     * @return zwraca dostęp do obiektu udostepnionego przez RMI
     */

    public SharingPicture getRmi() {
        return rmi;
    }
}
