/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mroziqella.RMIConnect;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import pl.mroziqella.inte.SharingPicture;


/**
 *
 * @author Kamil
 */
public class ClientRMI {

    private String address = null, nameServer = null;
    private int port = 0;
    private SharingPicture rmi;

    public ClientRMI(String address, String nameServer, int port) {
        this.address = address;
        this.nameServer = nameServer;
        this.port = port;
    }

    public void connect() throws RemoteException, NotBoundException {
        Registry reg = LocateRegistry.getRegistry(address, port);
        rmi = (SharingPicture ) reg.lookup(nameServer);
        System.out.println("Conencted");
    }
    /**
     * Daje dostep do metod udostepnionych przez RMI
     * @return 
     */
    public SharingPicture getRmi() {
        return rmi;
    }
    
    //##########################TEST##############################

    public static void main(String[] args) {
        ClientRMI client = new ClientRMI("127.0.0.1", "server", 2000);
        String test;
        try {
            client.connect();
            test=client.getRmi().getTest();
            Logger.getLogger(ClientRMI.class.getName()).info(Arrays.toString(client.getRmi().readImageFromServer("kamil").getImage()));
            if (test.equals("Test"))
                System.out.println("TEST: Completed");
            else
                System.out.println("TEST: Failed");
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ClientRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
