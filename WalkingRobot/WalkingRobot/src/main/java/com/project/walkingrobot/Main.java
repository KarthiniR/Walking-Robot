package com.project.walkingrobot;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private Robot robot;
    private Floor floor;

    private Label positionLabel;
    private GridPane floorGrid;
    private TextField commandTextField;
    private boolean displayTracedPath;
    private List<int[]> tracedPathPositions = new ArrayList<>();
    private List<int[]> OmittedPathPositions = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        robot = null;
        floor = null;

        primaryStage.setTitle("Robot Simulator");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        Label commandLabel = new Label("Enter Command:");
        commandTextField = new TextField();

        Button executeButton = new Button("Execute");
        executeButton.setOnAction(event -> executeCommand());

        positionLabel = new Label("Position: -\nPen: -\nFacing: -");

        floorGrid = new GridPane();
        floorGrid.setAlignment(Pos.CENTER);
        floorGrid.setHgap(2);
        floorGrid.setVgap(2);

        gridPane.add(positionLabel, 0, 0, 1, 2);
        gridPane.add(commandLabel, 0, 2);
        gridPane.add(commandTextField, 1, 2);
        gridPane.add(executeButton, 2, 2);
        gridPane.add(floorGrid, 0, 3, 3, 1);

        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeFloorGrid(int size) {
        floorGrid.getChildren().clear();
        floorGrid.getColumnConstraints().clear();
        floorGrid.getRowConstraints().clear();

        for (int row = size - 1; row >= 0; row--) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.ALWAYS);
            floorGrid.getRowConstraints().add(rowConstraints);

            for (int col = 0; col < size; col++) {
                ColumnConstraints colConstraints = new ColumnConstraints();
                colConstraints.setHgrow(Priority.ALWAYS);
                floorGrid.getColumnConstraints().add(colConstraints);

                Rectangle rectangle = new Rectangle(30, 30);
                rectangle.setFill(Color.WHITE);
                rectangle.setStroke(Color.BLACK);

                if (row == size - 1 && col == 0) {
                    rectangle.setFill(Color.RED); // Starting position
                }

                floorGrid.add(rectangle, col, row);
            }
        }
    }

    private void executeCommand() {
        String input = commandTextField.getText().trim();

        String[] command = input.split(" ");
        String cmd = command[0];

        if (cmd.equalsIgnoreCase("I")) {
            int size = Integer.parseInt(command[1]);
            floor = new Floor(size);
            robot = new Robot();
            updatePositionLabel();
            initializeFloorGrid(size);
        } else if (cmd.equalsIgnoreCase("C")) {
            if (robot != null) {
                updatePositionLabel();
            } else {
                System.out.println("Please initialize the system first!");
            }
        } else if (cmd.equalsIgnoreCase("U")) {
            if (robot != null) {
                robot.setPenDown(false);
            } else {
                System.out.println("Please initialize the system first!");
            }
        } else if (cmd.equalsIgnoreCase("D")) {
            if (robot != null) {
                robot.setPenDown(true);
            } else {
                System.out.println("Please initialize the system first!");
            }
        } else if (cmd.equalsIgnoreCase("R")) {
            if (robot != null) {
                robot.turnRight();
            } else {
                System.out.println("Please initialize the system first!");
            }
        } else if (cmd.equalsIgnoreCase("L")) {
            if (robot != null) {
                robot.turnLeft();
            } else {
                System.out.println("Please initialize the system first!");
            }
        } else if (cmd.equalsIgnoreCase("M")) {
            if (robot != null) {
                int steps = Integer.parseInt(command[1]);
                for (int i = 0; i < steps; i++) {
                    robot.move(1);
                    floor.markPosition(robot.getX(), robot.getY());
                }
                displayTracedPath = false;
                updateFloorGrid();
                updatePositionLabel();
            } else {
                System.out.println("Please initialize the system first!");
            }
        } else if (cmd.equalsIgnoreCase("P")) {
            if (floor != null) {
                displayTracedPath = true;
                fillTracedPath();
            } else {
                System.out.println("Please initialize the system first!");
            }
        } else if (cmd.equalsIgnoreCase("Q")) {
            resetTracedPath();
            System.out.println("End of program.");
        } else {
            System.out.println("Invalid command. Please try again!");
        }
        commandTextField.clear();
    }





    private void fillTracedPath() {
        if (displayTracedPath) {
            for (int[] position : tracedPathPositions) {
                int col = position[0];
                int row = position[1];
                Rectangle rectangle = new Rectangle(30, 30);
                rectangle.setStroke(Color.BLACK);

                if (row == robot.getY() && col == robot.getX()) {
                    rectangle.setFill(Color.RED);
                } else {
                    rectangle.setFill(Color.BLACK);
                }

                floorGrid.add(rectangle, col, row);
            }
            resetTracedPath();
        }
    }
    private void resetTracedPath() {
        tracedPathPositions.clear();
    }


    private void updatePositionLabel() {
        String penStatus = robot.isPenDown() ? "down" : "up";
        String positionText = "Position: " + robot.getX() + ", " + robot.getY() +
                "\nPen: " + penStatus + "\nFacing: " + robot.getDirection();
        positionLabel.setText(positionText);
    }
}