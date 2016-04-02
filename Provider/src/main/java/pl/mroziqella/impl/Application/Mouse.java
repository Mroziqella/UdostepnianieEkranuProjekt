/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mroziqella.impl.Application;

import java.awt.AWTException;
import java.awt.Robot;

/**
 *
 * @author Kamil
 */
public class Mouse {

    private static int coordinatesX, coordinatesY;
    private Robot robot;

    /**
     * 
     * @throws AWTException
     */
    public Mouse() throws AWTException {

        robot = new Robot();
        robot.mouseMove(coordinatesX, coordinatesY);
    }

    /** 
     * Ustawia współrzędne na jakich ma się znaleźć kursor myszy
     * @param coordinatesX szerokość
     * @param coordinatesY wysokość
     */
    public void setCoordinatesXY(int coordinatesX, int coordinatesY) {
        Mouse.coordinatesX = coordinatesX;
        Mouse.coordinatesY = coordinatesY;

    }

    /**
     * Kliknięcie myszą
     * @param status
     * @param mouseKet
     */
    public void MouseClick(boolean status,int mouseKet) {
        if (status) {
            robot.delay(100);
            robot.mouseMove(coordinatesX, coordinatesY);
            robot.delay(20);
            robot.mousePress(mouseKet);
            robot.delay(20);
            robot.mouseRelease(mouseKet);
        }
    }

}
