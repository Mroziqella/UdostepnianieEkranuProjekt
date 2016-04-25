package pl.mroziqella.service;


import pl.mroziqella.exception.ImageNotFound;

/**
 * Created by Kamil on 25/03/2016.
 */

public interface ShareService {
    byte[] getImageBase64(String login) throws ImageNotFound;
}
