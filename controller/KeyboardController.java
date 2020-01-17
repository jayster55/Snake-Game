package controller;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import static controller.Game.setLevel;
import static controller.GameShell.*;
import static view.Grid.snake;

public class KeyboardController {

    public static void newKeyCommand(Stage primaryStage, KeyCode keycode) {
        if (game.isKeyPressed()) {
            return;
        }
        switch (keycode) {
            case DIGIT1:
                reset(primaryStage);
                setLevel(1);
                getNextScene(primaryStage);
                break;
            case DIGIT2:
                reset(primaryStage);
                setLevel(2);
                getNextScene(primaryStage);
                break;
            case DIGIT3:
                reset(primaryStage);
                setLevel(3);
                getNextScene(primaryStage);
                break;

            case UP:
                if (!game.isPaused()) snake.goUp();
                break;
            case DOWN:
                if (!game.isPaused()) snake.goDown();
                break;
            case LEFT:
                if (!game.isPaused()) snake.goLeft();
                break;
            case RIGHT:
                if (!game.isPaused()) snake.goRight();
                break;
            case P:
                if (snake.isSnakeStationary()) {
                    game.resumeGame();
                    snake.resumeSnake();
                }
                else {
                    game.pauseGame();
                    snake.pauseSnake();
                }
                break;
            case Q:
                setLevel(4);
                getNextScene(primaryStage);
//                reset(primaryStage);
                break;
            case R:
                reset(primaryStage);
                break;
            default:
                break;
        }
    }
}
