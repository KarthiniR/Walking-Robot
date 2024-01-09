package com.project.walkingrobot;

import java.util.ArrayList;
import java.util.List;

public class Robot {
    private int x;
    private int y;
    private boolean penDown;
    private Direction direction;
    private final List<int[]> tracedPathPositions;

    public Robot() {
        x = 0;
        y = 0;
        penDown = false;
        direction = Direction.NORTH;
        tracedPathPositions = new ArrayList<>();
        tracedPathPositions.add(new int[]{x, y}); // Add initial position to traced path
    }

    public void move(int steps, Floor floor) {
        if (steps <= 0) {
            throw new IllegalArgumentException("Invalid number of steps: " + steps);
        }

        for (int i = 0; i < steps; i++) {
            int nextX = x;
            int nextY = y;

            switch (direction) {
                case NORTH -> nextY++;
                case EAST -> nextX++;
                case SOUTH -> nextY--;
                case WEST -> nextX--;
            }

            if (!floor.isValidPosition(nextX, nextY)) {
                throw new IllegalArgumentException("Can't move further, give a valid number of steps.");
            }

            if (floor.isValidPosition(nextX, nextY)) {
                x = nextX;
                y = nextY;

                if (penDown) {
                    if (i == 0) {
                        floor.markPosition(x, y);  // Mark initial position
                        tracedPathPositions.add(new int[]{x, y});
                    } else {
                        floor.markPosition(x, y);
                        tracedPathPositions.add(new int[]{x, y});
                    }
                }
            }
        }
    }





    public void turnRight() {
        switch (direction) {
            case NORTH -> direction = Direction.EAST;
            case EAST -> direction = Direction.SOUTH;
            case SOUTH -> direction = Direction.WEST;
            case WEST -> direction = Direction.NORTH;
        }
    }

    public void turnLeft() {
        switch (direction) {
            case NORTH -> direction = Direction.WEST;
            case EAST -> direction = Direction.NORTH;
            case SOUTH -> direction = Direction.EAST;
            case WEST -> direction = Direction.SOUTH;
        }
    }

    public void printPosition() {
        String penStatus = penDown ? "down" : "up";
        String positionString = "Position: " + x + ", " + y + " - Pen: " + penStatus + " - Facing: " + direction;
        System.out.println(positionString);
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

    public boolean isPenDown() {
        return penDown;
    }

    public void setPenDown(boolean penDown) {
        this.penDown = penDown;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public List<int[]> getTracedPathPositions() {
        return tracedPathPositions;
    }
    }
