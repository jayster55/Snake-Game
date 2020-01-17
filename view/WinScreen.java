package view;

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

import static controller.GameShell.reset;


public class WinScreen extends StackPane {

    // ------------Constructor------------
    public WinScreen(Stage primaryStage, int highscore) {

        this.setOnKeyPressed(e -> {
            KeyboardController.newKeyCommand(primaryStage, e.getCode());
        });

        Text gameOver = new Text("VICTORY!");
        gameOver.setFont(Font.font ("Arial", 60));
        gameOver.setFill(Color.GREEN);

        Text highscoreTitle = new Text("YOUR HIGHSCORE WAS: " + highscore);
        highscoreTitle.setFont(Font.font ("Arial", 30));
        highscoreTitle.setFill(Color.BLACK);

        Button homescreen = new Button("Return to Main Screen");
        homescreen.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                reset(primaryStage);
//                getNextScene(primaryStage);
            }
        });

        VBox vbox = new VBox(8); // spacing =
        vbox.setMaxHeight(800);// 8
        vbox.setMaxWidth(1280);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        vbox.getChildren().addAll(
                gameOver,
                highscoreTitle,
                homescreen
        );

        this.getChildren().add(vbox);
        this.setPrefSize(1280, 800);
        setAlignment(vbox, Pos.CENTER);

    }


}
