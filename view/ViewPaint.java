package view;

import javafx.scene.paint.Color;
import model.Apple;
import model.GridPoint;
import model.Snake;

import static controller.Game.getLevel;
import static controller.GameShell.*;
import static view.Grid.size;
import static view.Grid.snake;

public class ViewPaint {

    // ----------Class Functions----------
    // [PUBLIC FUNCTIONS]
    public static void paintView() {

        context.setFill(Grid.COLOR);
        context.fillRect(0, 0, grid.getWidth(), grid.getHeight());
        context.setFill(Snake.COLOR);
        snake.getGridPoints().forEach(ViewPaint::paintGridPoint);

        if (!snake.stillAlive()) {
            context.setFill(Snake.DEAD);
            paintGridPoint(snake.getHead());
        }

        context.setFill(Apple.COLOR);
        paintGridPoint(grid.getApple().getAppleLocation());

        context.setFill(Color.BLACK);
        context.fillText("Score: " + snake.getGridPoints().size() + ", Level: " + getLevel(), 10, 20);

        if (getLevel() != 3) {
            context.setFill(Color.RED);
            context.fillText("Timer: " + getLevelTimer(), 10, 40);
        }

    }

    public static void paintPausedMessage() {
        context.setFill(Color.RED);
        context.fillText("GAME PAUSED", 200,200);
    }

    public static void paintResetMessage() {
        context.setFill(Color.AQUAMARINE);
        context.fillText("Hit RETURN to reset.", 10, 10);
    }

    public static int getScore(){
        if (grid.getSnake() != null) {
            return grid.getSnake().getGridPoints().size();
        } else return 0;
    }
    // [PRIVATE FUNCTIONS]
    private static void paintGridPoint(GridPoint point) {
        context.fillRect(point.getX() * size, point.getY() * size, size, size);
    }


}