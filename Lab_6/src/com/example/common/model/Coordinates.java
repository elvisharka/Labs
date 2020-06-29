package com.example.common.model;

import java.io.Serializable;

/**
 * Entity.
 */
public class Coordinates implements Serializable {
    private static final long serialVersionUID = 2245760389409118764L;
    private long x; //Максимальное значение поля: 278
    private long y; //Максимальное значение поля: 500

    public Coordinates(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
