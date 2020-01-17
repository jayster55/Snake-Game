package model;


public class GridPoint {

    // ---------Class Variables-----------
    private final int x;
    private final int y;

   // ------------Constructor------------
    public GridPoint(final int x, final int y) { this.x = x; this.y = y; }

    // ----------Class Functions----------
    // [PUBLIC FUNCTIONS]
    public String toString() { return x + ", " + y; }
    public boolean equals(GridPoint point) {
        return x == point.x & y == point.y;
    }
    public int getX() { return x; }
    public int getY() { return y; }

    // [PRIVATE FUNCTIONS]
    GridPoint translate(int dx, int dy) { return new GridPoint(x + dx, y + dy); }
}