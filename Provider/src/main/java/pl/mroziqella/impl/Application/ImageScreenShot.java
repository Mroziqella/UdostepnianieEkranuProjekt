/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mroziqella.impl.Application;

import pl.mroziqella.inte.*;
import pl.mroziqella.inte.Image;
import pl.mroziqella.inte.MouseInfo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Kamil
 */
public class ImageScreenShot implements Runnable {

    private byte[] imageByteArray;
    private Robot robot;
    private Thread thread;
    private Dimension dimension;
    private Rectangle rectangle;
    private BufferedImage screen;
    private boolean screenRun;
    private int qualityProcent = 100;
    private double zoom=1;
    private SharingPicture rmi;
    private String login;
    private Image image;

    /**
     * Tworzy obiekt robiacy i zrzuty ekranu
     *
     * @throws AWTException
     */
    public ImageScreenShot(SharingPicture rmi,String login) throws AWTException {
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        rectangle = new Rectangle(dimension);
        robot = new Robot();
        screenRun = false;
        this.rmi = rmi;
        this.login=login;

    }


    /**
     * Robienie zrzutow uruchomione
     */
    public void screenRunStart() {
        this.screenRun = true;
    }

    /**
     * Robienie zrzutow wstrzymane
     */
    public void screenRunStop() {
        this.screenRun = false;
    }

    /**
     * Ustawienie jaka jakośc obrazu ma być przesyłana
     *
     * @param qualityProcent jakość w procentach
     */
    public void setQuality(int qualityProcent) {
        this.qualityProcent = qualityProcent;
        zoom=100.0/qualityProcent;
    }

    /**
     * Udostępnia tablicę która przechowuje obraz
     *
     * @return tablica z obrazem
     */
    public byte[] getImageByteArray() {
        return imageByteArray;
    }

    /**
     * Uruchamia wątek tworzency zrzuty ekranu
     *
     * @throws AWTException
     */
    public void startThreadScreen() throws AWTException {
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Watek tworzacy zrzuty ekranu
     */
    @Override
    public void run() {
        screenRun = true;
        while (screenRun) {
            this.screenCapture();
            try {

                rmi.writeImageToServer(new Image(null,imageByteArray,null,zoom),login);
                Thread.sleep(1000);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }

    /**
     * Tworzy zrzut obrazu i wywoluje metody je przetwarzajace
     */
    private void screenCapture() {
        screen = robot.createScreenCapture(rectangle);
        scaleImage(qualityProcent);
        convertToArray();

    }

    /**
     * Skaluje obraz
     *
     * @param procent wartość procentowa jak ma byc przeskalowany obraz
     */
    private void scaleImage(double procent) {
        int width = screen.getWidth();
        int height = screen.getHeight();
        procent = procent / 100;
        AffineTransform scale = AffineTransform.getScaleInstance(width / width * procent, height / height * procent);
        BufferedImageOp op = new AffineTransformOp(scale, AffineTransformOp.TYPE_BILINEAR);
        BufferedImage filteredImage = new BufferedImage((int) (width * procent), (int) (height * procent), screen.getType());
        op.filter(screen, filteredImage);
        screen = filteredImage;
    }

    /**
     * Zamienia obraz na tablicę
     */
    private void convertToArray() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(screen, "jpg", baos);
            baos.flush();
            imageByteArray = baos.toByteArray();
            baos.close();
        } catch (IOException ex) {
            Logger.getLogger(ImageScreenShot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
