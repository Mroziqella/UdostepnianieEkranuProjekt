package pl.mroziqella.inte;

import pl.mroziqella.inte.MouseInfo;

import java.io.Serializable;
import java.util.Base64;
import java.util.Calendar;

/**
 * Created by Kamil on 29/03/2016.
 */
public class Image implements Serializable {

    private byte[] image;
    private byte[] imageBase64;
    private double zoom;
    private Calendar time;

    public Image(Calendar time, byte[] image, byte[] imageBase64, double zoom) {
        this.time = time;
        this.image = image;
        this.imageBase64 = imageBase64;
        this.zoom = zoom;
    }

    public byte[] getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(byte[] imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public byte[] getImage() {
        //byte[] encoded = Base64.getEncoder().encode(image);
        return image;
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

    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }
}
