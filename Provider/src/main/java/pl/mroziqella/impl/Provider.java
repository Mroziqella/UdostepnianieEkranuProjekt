package pl.mroziqella.impl;

import pl.mroziqella.impl.Application.ImageScreenShot;
import pl.mroziqella.impl.Application.Mouse;
import pl.mroziqella.inte.SharingPicture;

import java.awt.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * lab3
 * Created by Kamil on 21/03/2016.
 */
public class Provider implements Runnable{

    private String address = null, nameServer = null;
    private int port = 0;
    private SharingPicture rmi;
    private ImageScreenShot imageScreenShot;
    private Mouse mouse;
    private String login;
    private boolean shareMode =false;

    public Provider(String address, String nameServer, int port) {
        this.address = address;
        this.nameServer = nameServer;
        this.port = port;
    }

    public ImageScreenShot getImageScreenShot() {
        return imageScreenShot;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public void run() {
        try {
            start(login);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void start(String login) throws RemoteException, NotBoundException, AWTException {
        String test;
        this.connect();
        test = this.getRmi().getTest();
        if (test.equals("Test"))
            System.out.println("TEST: Completed");
        else
            System.out.println("TEST: Failed");


        imageScreenShot = new ImageScreenShot(this.getRmi(),login);
        new Thread(imageScreenShot).start();
        mouse = new Mouse(login,shareMode,getRmi());
        new Thread(mouse).start();

    }
    public void stop(){
        imageScreenShot.screenRunStop();
        mouse.mouseRunStop();
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

    //##########################TEST##############################

    /**
     * Daje dostep do metod udostepnionych przez RMI
     *
     * @return zwraca dostęp do obiektu udostepnionego przez RMI
     */

    public SharingPicture getRmi() {
        return rmi;
    }

    /**
     * Ustawia flage pozwalajaca na zdalne sterowanie
     * @param shareMode
     */
    public void setShareMode(boolean shareMode) {
        this.shareMode = shareMode;
        mouse.setShareMode(shareMode);
    }
}
