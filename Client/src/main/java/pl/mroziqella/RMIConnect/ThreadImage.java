/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mroziqella.RMIConnect;

import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import pl.mroziqella.inte.SharingPicture;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


/**
 *
 * @author Kamil
 */
public class ThreadImage extends ClientRMI implements Runnable {

    private byte[] imageByteArray;
    private boolean statusThread;
    private ImageView dispaly = null;
    private int quality;
    private int sizePictureProcent = 100;
    private int width;
    private int height;
    private String login;
    private pl.mroziqella.inte.Image image;

    /**
     *
     * @param address adres ip
     * @param nameServer nazwa serwera
     * @param port port na ktorym ma nasluchiwac aplikacja
     * @param dispaly JLabel na którym ma byc wyswietlany obraz
     */
    public ThreadImage(String address, String nameServer, int port, ImageView dispaly,String login) {
        super(address, nameServer, port);
        this.statusThread = true;
        this.dispaly = dispaly;
        this.quality = 100;
        this.login = login;

    }

    public pl.mroziqella.inte.Image getImage() {
        return image;
    }



    /**
     * Ustawia wartości procentowe wielkości obrazu w celu jego późniejszego
     * przeskalowania metodą
     *
     * @param sizePictureProcent wartość procetowa musząca być wieksza od 0
     * @throws IllegalAccessException w przypadku wartości mniejszej od 0
     */
    public void setSizePictureProcent(int sizePictureProcent) throws IllegalAccessException {
        if (sizePictureProcent <= 0) {
            throw new IllegalAccessException("Argument musi być wiekszy od 0");
        }
        this.sizePictureProcent = sizePictureProcent;
    }

    /**
     * Ustawia jaka ma być jakość obrazu przysyłanego z serwera
     *
     * @param quality procentowa jakość obrazu
     */
    public void setQuality(int quality) {
        this.quality = quality;
    }

    @Override
    /**
     * Uruchamia wątek odpowiedzialny za pobieranie obrazu
     */
    public void run() {
        statusThread=true;
        while (statusThread) {
            downloandArrayImage();
        }
    }

    /**
     * Pobiera tablice z serwera
     */
    private void downloandArrayImage() {
        try {
            SharingPicture rmi = this.getRmi();
            image = rmi.readImageFromServer(login);
            imageByteArray = image.getImage();

            //Logger.getLogger(getClass().getName()).info(Arrays.toString(imageByteArray));
            convertArrayToImage();
        } catch (RemoteException ex) {
            dispaly.setImage(null);
            Logger.getLogger(ThreadImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Przekształaca tablice na obraz
     */
    private void convertArrayToImage() {
        try {
            BufferedImage bImageFromConvert;
            InputStream in = new ByteArrayInputStream(imageByteArray);
            bImageFromConvert = ImageIO.read(in);
            bImageFromConvert = scaleImage(bImageFromConvert);
            this.setWidthAndHeightImage(bImageFromConvert.getWidth(), bImageFromConvert.getHeight());
            this.showImage(bImageFromConvert);
        } catch (IOException ex) {
            Logger.getLogger(ThreadImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Skaluje podany obraz, % skalowania obrazu jest ustawiony w klasie jako
     * zmennia sizePictureProcent
     *
     * @param image obraz do skalowania
     * @return zwraca przeskalowany obraz do odpowiednich rozmiarów
     */
    private BufferedImage scaleImage(BufferedImage image) {
        return Image.scaleImage(image, sizePictureProcent);
    }

    /**
     * Wyswietla obaraz w jLabel podanym w konstruktorze obiektu
     *
     * @param image
     */
    private void showImage(BufferedImage image) {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }
        dispaly.setImage(wr);
    }

    /**
     * Zatrzymuje wątek odpowedzialny za pobieranie obrazu z serwera
     */
    public void stopThread() {
        statusThread = false;
    }

    /**
     *
     * @param width szerokość
     * @param height wysokość
     */
    public void setWidthAndHeightImage(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * 
     * @return zwraca wysokość obrazu
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @return zwraca szerokość obrazu
     */
    public int getWidth() {
        return width;
    }
}
