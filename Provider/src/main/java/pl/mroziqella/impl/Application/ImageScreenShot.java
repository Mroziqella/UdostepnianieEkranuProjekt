/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mroziqella.impl.Application;

import pl.mroziqella.inte.*;
import pl.mroziqella.inte.Image;
import pl.mroziqella.inte.MouseInfo;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Kamil
 */
public class ImageScreenShot implements Runnable {

    private byte[] imageByteArray;
    private Robot robot;
    private Dimension dimension;
    private Rectangle rectangle;
    private BufferedImage screen;
    private boolean screenRun;
    private int qualityProcent = 100;
    private double zoom=1;
    private SharingPicture rmi;
    private String login;

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
     * Watek tworzacy zrzuty ekranu
     */
    @Override
    public void run() {
        screenRun = true;
        while (screenRun) {
            this.screenCapture();
            try {
                rmi.writeImageToServer(new Image(null,imageByteArray,null,zoom,screen.getHeight(),screen.getWidth()),login);
                Thread.sleep(200);
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
           // ImageIO.write(screen, "jpg", baos);
            //baos.flush();
            writeJPG(screen,baos,0.1f);
            imageByteArray = baos.toByteArray();
            baos.close();
        } catch (IOException ex) {
            Logger.getLogger(ImageScreenShot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Zapis obrazu do JPG
     * @param bufferedImage
     * @param outputStream
     * @param quality
     * @throws IOException
     */
    public static void writeJPG(BufferedImage bufferedImage, OutputStream outputStream, float quality) throws IOException
    {
        Iterator<ImageWriter> iterator =
                ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter imageWriter = iterator.next();
        ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
        imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        imageWriteParam.setCompressionQuality(quality);
        ImageOutputStream imageOutputStream =
                new MemoryCacheImageOutputStream(outputStream);
        imageWriter.setOutput(imageOutputStream);
        IIOImage iioimage = new IIOImage(bufferedImage, null, null);
        imageWriter.write(null, iioimage, imageWriteParam);
        imageOutputStream.flush();
    }

}
