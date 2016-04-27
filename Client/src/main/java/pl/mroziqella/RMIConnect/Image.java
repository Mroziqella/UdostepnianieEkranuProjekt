/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mroziqella.RMIConnect;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

/**
 *
 * @author Kamil
 */
public class Image {
    /**
     * 
     * @param image obraz do skalowania
     * @param procent procenty do skalowania obrazu
     * @return zwraca obraz ze zmieniona wielkością
     */
    public static BufferedImage scaleImage(BufferedImage image,double procent) {
        int width = image.getWidth();
        int height = image.getHeight();
        procent = procent / 100;
        AffineTransform scale = AffineTransform.getScaleInstance(width / width * procent, height / height * procent);
        BufferedImageOp op = new AffineTransformOp(scale, AffineTransformOp.TYPE_BILINEAR);
        BufferedImage filteredImage = new BufferedImage((int) (width * procent), (int) (height * procent), image.getType());
        op.filter(image, filteredImage);
        return filteredImage;
    }

    
}



