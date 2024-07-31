package org.tairobea;

import java.awt.*;

class Pipe extends Entity {
    private boolean passed = false;

    private int velocity = -2;


    Pipe(int x, int y, int width, int height, Image img) {
        super(x, y, width, height, img);
    }

    void setPassed(boolean passed){
        this.passed = passed;
    }

    public boolean isPassed() {
        return passed;
    }

    public int getVelocity() {
        return velocity;
    }

}
