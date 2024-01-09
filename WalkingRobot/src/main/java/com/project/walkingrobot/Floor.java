package com.project.walkingrobot;

import java.util.Arrays;

public class Floor {
    private final int[][] floorArray;

    public Floor(int size) {
        floorArray = new int[size][size];
        for (int[] row : floorArray) {
            Arrays.fill(row, 0);
        }
    }

    public void markPosition(int x, int y) {
        if (isValidPosition(x, y)) {
            floorArray[floorArray.length - 1 - y][x] = 1;
        }
    }

    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < floorArray[0].length && y >= 0 && y < floorArray.length;
    }

    public int getCell(int x, int y) {
        if (isValidPosition(x, y)) {
            return floorArray[y][x];
        }
        return -1;
    }

    public int[][] getFloorArray() {
        return floorArray;
    }

    public int getFloorSize() {
        return floorArray.length;
    }
}
