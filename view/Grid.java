package view;

import javafx.scene.paint.Color;
import model.Apple;
import model.GridPoint;
import model.Snake;

import java.util.ArrayList;
import java.util.Random;
import static controller.Game.getLevel;

public class Grid {

    // ---------Class Variables-----------
    public static final int size = 10;
    public static final Color COLOR = Color.WHITE;
    private final int cols;
    private final int rows;
    private int appleNumber;
    ArrayList<Integer> applesX = new ArrayList<Integer>();
    ArrayList<Integer> applesY = new ArrayList<Integer>();
    public static Snake snake;
    private Apple apple;

    // ------------Constructor------------
    public Grid(final double width, final double height) {
        rows = (int) width / size;
        cols = (int) height / size;

        appleNumber = 0;

        addApples();
        snake = new Snake(new GridPoint(rows/2, cols/2));
        apple = new Apple(getNextApplePoint());
    }

    // ----------Class Functions----------
    // [PUBLIC FUNCTIONS]
    public GridPoint wrapSnake(GridPoint point) {
        int y = point.getY();
        int x = point.getX();
        if (y >= cols) y = 0;
        if (x >= rows) x = 0;
        if (y < 0) y = cols - 1;
        if (x < 0) x = rows - 1;

        return new GridPoint(x, y);
    }

    public void update() {
        if (ateApple()) {
            snake.extend();
            apple.setAppleLocation(getNextApplePoint());
        } else {
            snake.move();
        }
    }

    public double getWidth() { return rows * size; }
    public double getHeight() { return cols * size; }

    public Snake getSnake() { return snake; }
    public Apple getApple() { return apple; }

    public void addApples() {
        // adding x values
        applesY.add(5);
        applesY.add(10);
        applesY.add(15);
        applesY.add(50);
        applesY.add(70);
        applesY.add(65);
        applesY.add(60);
        applesY.add(55);
        applesY.add(5);
        applesY.add(10);
        applesY.add(15);
        applesY.add(30);
        applesY.add(25);
        applesY.add(45);
        applesY.add(40);
        applesY.add(35);
        applesY.add(30);
        applesY.add(25);
        applesY.add(20);
        applesY.add(20);


        applesX.add(75);
        applesX.add(80);
        applesX.add(120);
        applesX.add(45);
        applesX.add(40);
        applesX.add(35);
        applesX.add(30);
        applesX.add(25);
        applesX.add(115);
        applesX.add(110);
        applesX.add(50);
        applesX.add(55);
        applesX.add(60);
        applesX.add(65);
        applesX.add(70);
        applesX.add(95);
        applesX.add(90);
        applesX.add(85);
        applesX.add(105);
        applesX.add(100);

    }

    // [PRIVATE FUNCTIONS]
    private GridPoint getNextApplePoint() {
        if (appleNumber < 20) {
            Random random = new Random();
            GridPoint point;
            do {
                point = new GridPoint(applesX.get(appleNumber), applesY.get(appleNumber));
            } while (point.equals(snake.getHead()));
            appleNumber += 1;
            return point;
        }
        else if (getLevel() < 3) {
            return new GridPoint(0,0);
            // finished level, congrats, shouldn't return anymore points
        }
        else return getRandomGridPoint();
    }

    private GridPoint getRandomGridPoint() {
        Random random = new Random();
        GridPoint point;
        do {
            point = new GridPoint(random.nextInt(rows), random.nextInt(cols));
        } while (point.equals(snake.getHead()));

        return point;
    }

    private boolean ateApple() { return apple.getAppleLocation().equals(snake.getHead()); }
}