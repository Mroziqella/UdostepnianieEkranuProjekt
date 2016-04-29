package pl.mroziqella.inte;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface SharingPicture extends Remote {
    String getTest() throws RemoteException;
    void writeImageToServer(Image image, String login) throws  RemoteException;
    Image readImageFromServer(String login) throws RemoteException;
    byte[] readImageFromServerBase64(String login) throws RemoteException;
    boolean isUser(String login,String password) throws RemoteException;
    MouseInfo getMouseClick(String login) throws  RemoteException;
    void setMouseClick(String login,MouseInfo mouseInfo) throws RemoteException;
}
