import controller.GameShell;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static GameShell controller;
    // ------------Constructor------------
    public static void main(String[] args) {
        Application.launch(args);
    }

    // ----------Class Functions----------
    // [PUBLIC FUNCTIONS]
    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new GameShell(primaryStage);
    }
}