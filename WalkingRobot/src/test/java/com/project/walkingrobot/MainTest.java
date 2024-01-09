package com.project.walkingrobot;

import com.project.walkingrobot.Floor;
import com.project.walkingrobot.Main;
import com.project.walkingrobot.Robot;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.api.FxAssert;
import org.testfx.matcher.control.LabeledMatchers;
import javafx.stage.Stage;
import org.testfx.util.WaitForAsyncUtils;
import java.util.concurrent.TimeoutException;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class MainTest {

    Main main = new Main();
    Robot robot;
    Floor floor;
    Label positionLabel;
    TextField commandTextField;

    @Start
    private void start(Stage stage) {
        main.start(stage);
        robot = main.getRobot();
        floor = main.getFloor();
        positionLabel = main.getPositionLabel();
        commandTextField = main.getCommandTextField();
    }

    @BeforeEach
    public void setup() {
        robot = new Robot();
        floor = new Floor(10);
    }

    @Test
    public void testInitializeFloorGrid() {
        Platform.runLater(() -> {
            try {
                main.initializeFloorGrid(10);
            } catch (Exception e) {
                fail(e);
            }
        });

        WaitForAsyncUtils.waitForFxEvents();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        FxAssert.verifyThat("#floorGrid", Node::isVisible);
    }


    @Test
    public void testExecuteCommandI() {
        main.executeCommand("I 10", true);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        FxAssert.verifyThat(positionLabel, LabeledMatchers.hasText("Position: 0, 0\nPen: up\nFacing: NORTH"));
    }

    @Test
    public void testExecuteCommandU() {
        main.executeCommand("I 10", true);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        main.executeCommand("U", true);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        FxAssert.verifyThat(positionLabel, LabeledMatchers.hasText("Position: 0, 0\nPen: up\nFacing: NORTH"));
    }

    @Test
    public void testExecuteCommandD() {
        main.executeCommand("I 10", true);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        main.executeCommand("D", true);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        FxAssert.verifyThat(positionLabel, LabeledMatchers.hasText("Position: 0, 0\nPen: down\nFacing: NORTH"));
    }

    @Test
    public void testExecuteCommandR() {
        main.executeCommand("I 10", true);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        main.executeCommand("R", true);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        FxAssert.verifyThat(positionLabel, LabeledMatchers.hasText("Position: 0, 0\nPen: up\nFacing: EAST"));
    }

    @Test
    public void testExecuteCommandL() {
        main.executeCommand("I 10", true);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        main.executeCommand("L", true);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        FxAssert.verifyThat(positionLabel, LabeledMatchers.hasText("Position: 0, 0\nPen: up\nFacing: WEST"));
    }

    @Test
    public void testExecuteCommandM() {
        main.executeCommand("I 10", true);
        main.executeCommand("M 5", true);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        FxAssert.verifyThat(positionLabel, LabeledMatchers.hasText("Position: 0, 5\nPen: up\nFacing: NORTH"));
    }

}
