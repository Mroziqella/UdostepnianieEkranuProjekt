package pl.mroziqella.inte;

import java.io.Serializable;

/**
 * Created by Kamil on 28/04/2016.
 */
public class MouseInfo implements Serializable{
    private int x=0;
    private int y=0;
    private boolean click=true;

    public MouseInfo() {
    }

    public MouseInfo(int x, int y) {
        this.x = x;
        this.y = y;
        this.click = true;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }
}
