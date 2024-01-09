package com.project.walkingrobot;

import com.project.walkingrobot.Direction;
import com.project.walkingrobot.Floor;
import com.project.walkingrobot.Main;
import com.project.walkingrobot.Robot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;


public class RobotTest {

    private Robot robot;
    private Floor floor;

    @BeforeEach
    public void setup() {
        robot = new Robot();
        floor = new Floor(10);
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

    @Test
    public void testTurnLeftFromNorth() {
        robot.setDirection(Direction.NORTH);
        robot.turnLeft();
        Assertions.assertEquals(Direction.WEST, robot.getDirection());
    }

    @Test
    public void testTurnLeftFromWest() {
        robot.setDirection(Direction.WEST);
        robot.turnLeft();
        Assertions.assertEquals(Direction.SOUTH, robot.getDirection());
    }

    @Test
    public void testTurnLeftFromSouth() {
        robot.setDirection(Direction.SOUTH);
        robot.turnLeft();
        Assertions.assertEquals(Direction.EAST, robot.getDirection());
    }

    @Test
    public void testTurnLeftFromEast() {
        robot.setDirection(Direction.EAST);
        robot.turnLeft();
        Assertions.assertEquals(Direction.NORTH, robot.getDirection());
    }

    @Test
    public void testMoveNorthWithPenDown() {
        // Arrange
        robot.setPenDown(true);
        int steps = 3;

        // Act
        for (int i = 0; i < steps; i++) {
            robot.move(1, floor);
        }

        // Assert
        int[][] expectedFloorArray = new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        int[][] actualFloorArray = floor.getFloorArray();
        Assertions.assertArrayEquals(expectedFloorArray, actualFloorArray);
        System.out.println("Actual Floor Array:");
        for (int[] row : actualFloorArray) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void testMoveEastWithPenDown() {
        robot.setDirection(Direction.EAST);
        robot.setPenDown(true);

        int steps = 3;
        for (int i = 0; i < steps; i++) {
            robot.move(1, floor);
        }

        int[][] expectedFloorArray = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
        };

        int[][] actualFloorArray = floor.getFloorArray();
        Assertions.assertArrayEquals(expectedFloorArray, actualFloorArray);
    }

    @Test
    public void testMoveSouthWithPenDown() {
        robot.setDirection(Direction.SOUTH);
        robot.setPenDown(true);

        int steps = 3;

        for (int i = 0; i < steps; i++) {
            robot.move(1, floor);
        }

        int[][] expectedFloorArray = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        int[][] actualFloorArray = floor.getFloorArray();
        Assertions.assertArrayEquals(expectedFloorArray, actualFloorArray);
    }

    @Test
    public void testMoveWestWithPenDown() {
        robot.setDirection(Direction.WEST);
        robot.setPenDown(true);

        int steps = 3;

        for (int i = 0; i < steps; i++) {
            robot.move(1, floor);
        }

        int[][] expectedFloorArray = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        int[][] actualFloorArray = floor.getFloorArray();
        Assertions.assertArrayEquals(expectedFloorArray, actualFloorArray);
    }


    @Test
    public void testMoveNorthWithPenUp() {
        robot.setDirection(Direction.NORTH);
        robot.setPenDown(false);

        int steps = 3;
        for (int i = 0; i < steps; i++) {
            robot.move(1, floor);
        }

        int[][] expectedFloorArray = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        int[][] actualFloorArray = floor.getFloorArray();
        Assertions.assertArrayEquals(expectedFloorArray, actualFloorArray);
    }

    @Test
    public void testMoveEastWithPenUp() {
        robot.setDirection(Direction.EAST);
        robot.setPenDown(false);

        int steps = 3;
        for (int i = 0; i < steps; i++) {
            robot.move(1, floor);
        }

        int[][] expectedFloorArray = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        int[][] actualFloorArray = floor.getFloorArray();
        Assertions.assertArrayEquals(expectedFloorArray, actualFloorArray);
    }

    @Test
    public void testMoveSouthWithPenUp() {
        robot.setDirection(Direction.SOUTH);
        robot.setPenDown(false);

        int steps = 3;
        for (int i = 0; i < steps; i++) {
            robot.move(1, floor);
        }

        int[][] expectedFloorArray = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        int[][] actualFloorArray = floor.getFloorArray();
        Assertions.assertArrayEquals(expectedFloorArray, actualFloorArray);
    }

    @Test
    public void testMoveWestWithPenUp() {
        robot.setDirection(Direction.WEST);
        robot.setPenDown(false);

        int steps = 3;
        for (int i = 0; i < steps; i++) {
            robot.move(1, floor);
        }

        int[][] expectedFloorArray = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        int[][] actualFloorArray = floor.getFloorArray();
        Assertions.assertArrayEquals(expectedFloorArray, actualFloorArray);
    }


    @Test
    public void testTracePath() {
        robot.setPenDown(true);

        robot.move(2, floor);
        robot.turnRight();
        robot.move(3, floor);
        robot.turnLeft();
        robot.move(1, floor);

        List<int[]> expectedPath = List.of(
                new int[]{0, 0},
                new int[]{0, 1},
                new int[]{0, 2},
                new int[]{1, 2},
                new int[]{2, 2},
                new int[]{3, 2},
                new int[]{3, 3}
        );

        List<int[]> actualPath = robot.getTracedPathPositions();
        System.out.println("Expected Path Size: " + expectedPath.size());
        System.out.println(Arrays.deepToString(expectedPath.toArray()));
        System.out.println("Actual Path Size: " + actualPath.size());

        for (int i = 0; i < expectedPath.size(); i++) {
            Assertions.assertArrayEquals(expectedPath.get(i), actualPath.get(i), "Path position at index " + i + " is not equal");
        }
        System.out.println(Arrays.deepToString(actualPath.toArray()));
    }

    @Test
    public void testPrintPosition() {
        // Arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Robot robot = new Robot();
        robot.setX(3);
        robot.setY(4);
        robot.setPenDown(true);
        robot.setDirection(Direction.WEST);

        // Act
        robot.printPosition();

        // Assert
        String expectedOutput = "Position: 3, 4 - Pen: down - Facing: WEST";
        String actualOutput = outputStream.toString().trim();
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testInitialPosition() {
        Assertions.assertEquals(0, robot.getX());
        Assertions.assertEquals(0, robot.getY());
        Assertions.assertEquals(Direction.NORTH, robot.getDirection());
        Assertions.assertFalse(robot.isPenDown());
        Assertions.assertEquals(1, robot.getTracedPathPositions().size());
        Assertions.assertArrayEquals(new int[]{0, 0}, robot.getTracedPathPositions().get(0));
    }

    @Test
    public void testTerminationWithCommandQ() {
        // Arrange
        Main main = new Main();

        // Simulate user input "Q" by calling the executeCommand method with "Q"
        boolean terminateProgram = main.executeCommand("Q", true);

        // Assert
        Assertions.assertTrue(terminateProgram);
    }

}
