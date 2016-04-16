package pl.mroziqella.inte;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface SharingPicture extends Remote {
    String getTest() throws RemoteException;
    void writeImageToServer(byte[] image, String login) throws  RemoteException;
    byte[] readImageFromServer(String login) throws  RemoteException;
    boolean isUser(String login,String password) throws RemoteException;
}
