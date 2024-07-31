package org.tairobea;

import java.awt.*;

abstract class Entity {
    private int x;
    protected int y;

    private int width;
    private int height;
    private Image img;

    Entity(int x, int y, int width, int height, Image img){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getImg() {
        return img;
    }

    public void setX(int x) {
        this.x = x;
    }
}
