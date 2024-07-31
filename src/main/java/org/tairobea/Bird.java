package org.tairobea;

import java.awt.*;

class Bird extends Entity{

    Bird(int x, int y, int width, int height, Image img){
        super(x, y, width, height, img);
    }

    public void setY(int y){
        this.y = y;
    }

}
