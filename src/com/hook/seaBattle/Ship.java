package com.hook.seaBattle;

import java.util.Random;

/**
 * Created by User on 11.12.15.
 */
public class Ship {
    private Coordinates position;
    private int countPipe;
    private boolean vector;

    private static int[][] type = {{1}, new int[4], new int[3], new int[2], new int[1]};

    public Ship(Coordinates position, int countPipe, boolean vector) {
        this.position = position;
        this.countPipe = countPipe;
        this.vector = vector;
    }

    Ship(int countPipe) {
        Random random = new Random();
        this.countPipe = countPipe;
        this.vector = random.nextBoolean();
        this.position = new Coordinates(random.nextInt(10) + 1, random.nextInt(10) + 1);
    }

    public static int[][] getType() {
        return type;
    }

    public void setCountPipe(int countPipe) {
        this.countPipe = countPipe;
    }

    int getCountPipe() {
        return countPipe;
    }

    public boolean isVector() {
        return vector;
    }

    Coordinates getPosition() {
        return position;
    }

    boolean isVertical() {
        return vector;
    }
}