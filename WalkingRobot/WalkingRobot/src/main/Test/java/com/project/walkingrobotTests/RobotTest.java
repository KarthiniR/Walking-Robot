package com.project.walkingrobotTests;

import com.project.walkingrobot.Direction;
import com.project.walkingrobot.Main;
import com.project.walkingrobot.Robot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RobotTest {

    private Robot robot;
    private Main main;

    @BeforeEach
    public void setup() {
        robot = new Robot();
        main = new Main();
    }

    @Test
    public void testMovePenDown() {
        robot.setPenDown(true);
        Assertions.assertTrue(robot.isPenDown());
    }

    @Test
    public void testMovePenUp() {
        robot.setPenDown(false);
        Assertions.assertFalse(robot.isPenDown());
    }

    @Test
    public void testTurnRightFromNorth() {
        robot.setDirection(Direction.NORTH);
        robot.turnRight();
        Assertions.assertEquals(Direction.EAST, robot.getDirection());
    }

    @Test
    public void testTurnRightFromEast() {
        robot.setDirection(Direction.EAST);
        robot.turnRight();
        Assertions.assertEquals(Direction.SOUTH, robot.getDirection());
    }

    @Test
    public void testTurnRightFromSouth() {
        robot.setDirection(Direction.SOUTH);
        robot.turnRight();
        Assertions.assertEquals(Direction.WEST, robot.getDirection());
    }

    @Test
    public void testTurnRightFromWest() {
        robot.setDirection(Direction.WEST);
        robot.turnRight();
        Assertions.assertEquals(Direction.NORTH, robot.getDirection());
    }




}
