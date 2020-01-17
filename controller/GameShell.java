package controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.*;

import java.util.concurrent.TimeUnit;

import static controller.Game.*;
import static view.ViewPaint.getScore;
//import static controller.Game.getLevel;

public class GameShell  {
    private static boolean started = false;

    // ---------Class Variables-----------
    private static final int Height = 800;
    private static final int Width = 1280;
    private static StackPane root = new StackPane();
    private static Scene rootScene;
    public static Game game;
    public static Grid grid;
    public static GraphicsContext context;
    public static long levelTimerStart;
    public static long levelTimerEnd;
    public static AnimationTimer timer;

    public GameShell(Stage primaryStage) {

        Canvas canvas = new Canvas(Width, Height);
        context = canvas.getGraphicsContext2D();

        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(e -> {
            KeyboardController.newKeyCommand(primaryStage, e.getCode());
        });

        root.getChildren().add(canvas);
        rootScene = new Scene(root);
        reset(primaryStage);

        primaryStage.setTitle("Snake");
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.show();
    }

    public static void getNextScene(Stage primaryStage) {
        int highscore = getScore();
        int level = getLevel();
        Scene newScene = null;
        switch (level) {
            case 0: // starting screen
                StartScreen startScreen = new StartScreen(primaryStage);
                newScene = new Scene(startScreen);
                primaryStage.setScene(newScene);
                break;
            case 4: // ending screen
                if (timer != null) timer.stop();
                DeadScreen deadscreen = new DeadScreen(primaryStage, highscore);
                newScene = new Scene(deadscreen);
                primaryStage.setScene(newScene);
                break;
            case 5:
                if (timer != null) timer.stop();
                WinScreen winscreen = new WinScreen(primaryStage, highscore);
                newScene = new Scene(winscreen);
                primaryStage.setScene(newScene);
                break;
            default: // game running
                game.changeLevelSpeed();
                setLevelTimer(System.currentTimeMillis(), 0);
                timer = new AnimationTimer() {
                    long curTime;

                    @Override
                    public void handle(long now) {
                        if (running && !dead) {
                            if (!started) {
                                curTime = now;
                                setLevelTimer(levelTimerStart, System.currentTimeMillis());
                                keyPressed = false;
                                grid.update();
                                ViewPaint.paintView();
                                started = true;
                            }

                            if ((now - curTime) > 1000000000/getFrameRate()) {
                                curTime = now;
                                setLevelTimer(levelTimerStart, System.currentTimeMillis());
                                grid.update();
                                ViewPaint.paintView();

                            }


                            if (!grid.getSnake().stillAlive()) {
                                setLevel(4);
                                getNextScene(primaryStage);
                            }

                            boolean winLevel = (getLevel() == 1 && getScore() >= 5)  // level 1 + 5 apples
                                    || (getLevel() == 2 && getScore() >= 10); // level 2 + 10 apples

                            if (winLevel) {
                                setLevel(5);
                                getNextScene(primaryStage);
                            }
                            if (getLevel() != 3 && getLevelTimer() <= 0) {
                                setLevel(4);
                                getNextScene(primaryStage);
                            }

                        }
                    }
                };
                timer.start();
                primaryStage.setScene(rootScene);
                break;
        }
    }

    public static long getLevelTimer(){
        return 30 - TimeUnit.MILLISECONDS.toSeconds(levelTimerEnd - levelTimerStart);
    }

    public static void setLevelTimer(long timestart, long timeend) {
        levelTimerStart = timestart;
        levelTimerEnd = timeend;
    }
    // [PRIVATE FUNCTIONS]
    public static void reset(Stage primaryStage) {
        grid = new Grid(Width, Height);
        if (game == null) game = new Game();
        setLevel(0);
        if (timer != null) timer.stop();
        game.changeLevelSpeed();
        game.resumeGame();
        ViewPaint.paintView();
        getNextScene(primaryStage);
    }
}
