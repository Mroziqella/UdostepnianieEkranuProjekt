/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mroziqella.impl.Application;

import pl.mroziqella.inte.MouseInfo;
import pl.mroziqella.inte.SharingPicture;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.rmi.RemoteException;

/**
 *
 * @author Kamil
 */
public class Mouse implements Runnable {

    private static int coordinatesX, coordinatesY;
    private Robot robot;
    private boolean mouseRun;
    private SharingPicture rmi;
    private String login;
    private MouseInfo mouseInfo;

    /**
     * 
     * @throws AWTException
     */
    public Mouse(String login, SharingPicture rmi) throws AWTException {
        this.login = login;
        this.rmi = rmi;
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
    public void mouseRunStop(){
        this.mouseRun = false;
    }

    /**
     * Kliknięcie myszą
     * @param status
     * @param mouseKet
     */
    public void mouseClick(boolean status,int mouseKet) throws RemoteException {
        if (status) {
            robot.delay(100);
            robot.mouseMove(mouseInfo.getX(), mouseInfo.getY());
            robot.delay(20);
            robot.mousePress(mouseKet);
            robot.delay(20);
            robot.mouseRelease(mouseKet);
            mouseInfo.setClick(false);
            rmi.setMouseClick(login,mouseInfo);
        }
    }
    @Override
    public void run() {
        mouseRun = true;
        while (mouseRun) {
            try {
                mouseInfo = rmi.getMouseClick(login);
                this.mouseClick(mouseInfo.isClick(), InputEvent.BUTTON1_MASK);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

    }
}
