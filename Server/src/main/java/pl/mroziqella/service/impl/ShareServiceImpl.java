package pl.mroziqella.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mroziqella.inte.SharingPicture;
import pl.mroziqella.repository.server.Server;
import pl.mroziqella.service.ShareService;

import java.rmi.RemoteException;
import java.util.Base64;

/**
 * Created by Kamil on 26/03/2016.
 */

/**
 * Do wyswietlania w przegladarce
 */
@Service
public class ShareServiceImpl implements ShareService{
    @Autowired
    private SharingPicture sharingPicture;
    @Override
    public byte[] getImage(String login) {
        try {
            return  Base64.getEncoder().encode(sharingPicture.readImageFromServer(login));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}
