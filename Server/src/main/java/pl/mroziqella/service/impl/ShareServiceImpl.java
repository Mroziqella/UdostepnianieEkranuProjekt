package pl.mroziqella.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mroziqella.exception.ImageNotFound;
import pl.mroziqella.inte.SharingPicture;
import pl.mroziqella.repository.server.Server;
import pl.mroziqella.service.ShareService;

import java.io.Serializable;
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


    @Override
    public byte[] getImageBase64(String login) {
        try {
            return Server.imageData.get(login).getImageBase64();
        } catch (NullPointerException e) {
            throw new ImageNotFound();
        }

    }
}
