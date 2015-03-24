package com.example.tommaso.suncity;

/**
 * Created by tommaso on 24/03/15.
 */
abstract public class Building {
    private final int width;
    private final int height;

    protected Building(int w, int h) {
        this.width = w;
        this.height = h;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
