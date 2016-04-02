package pl.mroziqella.domain;

import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Kamil on 29/03/2016.
 */
public class Image  {

    private byte[] image;
    private Calendar time;

    public Image(Calendar time, byte[] image) {
        this.time = time;
        this.image = image;
    }

    public byte[] getImage() {
        byte[] encoded = Base64.getEncoder().encode(image);
        return encoded;
    }


    public void setImage(byte[] image) {
        this.image = image;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }
}
