package model;

import javafx.scene.paint.Color;

public class Apple {

    // ---------Class Variables-----------
    public static Color COLOR = Color.RED;
    private GridPoint location;

    // ------------Constructor------------
    public Apple(GridPoint point) { this.location = point; }

    // ----------Class Functions----------
    // [PUBLIC FUNCTIONS]
    public void setAppleLocation(GridPoint point) { this.location = point; }
    public GridPoint getAppleLocation() { return location; }

}