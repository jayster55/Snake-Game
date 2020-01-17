package controller;

public class Game {

    // ---------Class Variables-----------
    private static int frameRate = 25;
    public static boolean keyPressed = false;
    private static int level = 0;
    private static boolean paused = false;
    public static boolean dead = false;
    public static boolean running = true;

    public void pauseGame(){ paused = true; }
    public void resumeGame() { paused = false; }
    public boolean isPaused() { return paused; }

    public boolean isKeyPressed() { return keyPressed; }

    public void killSnake() { dead = true; }

    public static int getLevel() { return level; }
    public static void setLevel(int newLevel) { level = newLevel; };

    public static int getFrameRate() { return frameRate; }
    public void changeLevelSpeed() {
        switch(level) {
            case 2:
                frameRate = 35;
                break;
            case 3:
                frameRate = 45;
                break;
            default:
                frameRate = 25;
                break;
        }
    }

}