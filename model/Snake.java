package model;

import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

import static controller.GameShell.grid;

public class Snake {

    // ---------Class Variables-----------
    public static final Color COLOR = Color.GREEN;
    public static final Color DEAD = Color.RED;

    private int length;
    private boolean safe;
    private List<GridPoint> points;
    private GridPoint head;
    private int previousXVelocity;
    private int previousYVelocity;
    private int velocityX;
    private int velocityY;

    // ------------Constructor------------
    public Snake(GridPoint initialGridPoint) {
        length = 1;
        safe = true;
        velocityX = 0;
        velocityY = 0;

        points = new LinkedList<>();
        points.add(initialGridPoint);
        head = initialGridPoint;
    }

    // ----------Class Functions------
    // [PUBLIC FUNCTIONS]
    public List<GridPoint> getGridPoints() { return points; }

    public boolean stillAlive() { return safe || length == 1; }
    public GridPoint getHead() { return head; }
    public boolean isSnakeStationary() {
        return velocityX == 0 & velocityY == 0;
    }

    public void pauseSnake() {
        previousXVelocity = velocityX;
        previousYVelocity = velocityY;
        velocityX = 0;
        velocityY = 0;
    }

    public void resumeSnake() {
        velocityX = previousXVelocity;
        velocityY = previousYVelocity;
    }

    public void move() {
        if (!isSnakeStationary()) {
            moveTo(head.translate(velocityX, velocityY));
        }
    }

    public void extend() {
        if (!isSnakeStationary()) { growTo(head.translate(velocityX, velocityY)); }
    }

    public void goUp() {
        if (velocityY == 1 && length > 1) return;
        velocityX = 0;
        velocityY = -1;
    }
    public void goDown() {
        if (velocityY == -1 && length > 1) return;
        velocityX = 0;
        velocityY = 1;
    }
    public void goLeft() {
        if (velocityX == 1 && length > 1) return;
        velocityX = -1;
        velocityY = 0;
    }
    public void goRight() {
        if (velocityX == -1 && length > 1) return;
        velocityX = 1;
        velocityY = 0;
    }
    // [PRIVATE FUNCTIONS]
    private void growTo(GridPoint point) {
        length++;
        extendSnake(point);
    }
    private void moveTo(GridPoint point) {
        extendSnake(point);
        points.remove(0);
    }
    private void extendSnake(GridPoint point) {
        point = grid.wrapSnake(point);
        safe &= !points.contains(point);
        points.add(point);
        head = point;
    }
}