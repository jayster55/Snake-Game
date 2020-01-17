package view;

import controller.Game;
import controller.KeyboardController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static controller.GameShell.getNextScene;

public class StartScreen extends StackPane {

    // ------------Constructor------------
    public StartScreen(Stage primaryStage) {

        this.setOnKeyPressed(e -> {
            KeyboardController.newKeyCommand(primaryStage, e.getCode());
        });

        Text title = new Text("Welcome to Snake");
        title.setFont(Font.font ("Arial", 60));
        title.setFill(Color.GREEN);

        Text madeBy = new Text("Made By Jared Gee");
        madeBy.setFont(Font.font ("Arial", 30));
        madeBy.setFill(Color.BLACK);

        Text userID = new Text("j2gee");
        userID.setFont(Font.font ("Arial", 20));
        userID.setFill(Color.DARKGREY);

        Text instructionsTitle = new Text("Game Instructions");
        instructionsTitle.setFont(Font.font ("Arial", 20));
        instructionsTitle.setFill(Color.INDIANRED);

        Text instructions1 = new Text("Arrow keys: turn the snake in the direction indicated");
        Text instructions2 = new Text("P: pause and un-pause the game");
        Text instructions3 = new Text("R: reset to the splash screen");
        Text instructions4 = new Text("1: start level 1");
        Text instructions5 = new Text("2: start level 2");
        Text instructions6 = new Text("3: start level 3");
        Text instructions7 = new Text("Q: quit and display the high score screen");

        Button level1 = new Button("Level 1");
        level1.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Game.setLevel(1);
                getNextScene(primaryStage);
            }
        });

        Button level2 = new Button("Level 2");
        level2.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Game.setLevel(2);
                getNextScene(primaryStage);
            }
        });

        Button level3 = new Button("Level 3");
        level3.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Game.setLevel(3);
                getNextScene(primaryStage);
            }
        });

        VBox vbox = new VBox(8);
        vbox.setMaxHeight(800);
        vbox.setMaxWidth(1280);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        vbox.getChildren().addAll(
                title,
                madeBy,
                userID,
                instructionsTitle,
                instructions1,
                instructions2,
                instructions3,
                instructions4,
                instructions5,
                instructions6,
                instructions7,
                level1,
                level2,
                level3
        );

        this.getChildren().add(vbox);
        this.setPrefSize(1280, 800);
        setAlignment(vbox, Pos.CENTER);


    }

}
