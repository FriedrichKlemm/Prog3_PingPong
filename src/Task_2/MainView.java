package Task_2;

import Task_2.Controller.Animation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;

import static javafx.scene.layout.BackgroundSize.*;

public class MainView {
    private Animation animation;
    private final MenuBar menuBar;
    private final Scene scene;
    private final MenuItem newGame;
    private final MenuItem exit;
    private final Menu file;
    private final Group group;

    private Label poitns;
    private final Label textScore;
    private Label gameOver;
    private final Button pauseButton;
    private String imageURL;


    private final BorderPane statusbar, windowPane;
    private Pane field;

    private final VBox vBox;

    public MainView() {

        this.group = new Group();

        menuBar = new MenuBar();

        newGame = new MenuItem("_New Game");
        newGame.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN));
        newGame.setOnAction(Event -> {
            animation.restart();
            animation = new Animation(this);
            gameOver.setVisible(false);
            poitns.setText("0");
            field.requestFocus();
        });

        exit = new MenuItem("_Spiel verlassen");
        exit.setOnAction(Event -> System.exit(0));
        exit.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.SHORTCUT_DOWN));

        file = new Menu("_Spiel");
        file.getItems().addAll(newGame, exit);

        menuBar.getMenus().addAll(file);

        textScore = new Label("Score: ");
        textScore.setStyle("-fx-font-size: 150%");


        poitns = new Label("0");
        poitns.setStyle("-fx-font-size: 150%");
        poitns.setAlignment(Pos.CENTER);

        gameOver = new Label("GameOver");
        gameOver.setStyle("-fx-font-size: 150%");
        //gameOver.setStyle("-fx-background-color: firebrick");
        gameOver.setAlignment(Pos.CENTER);


        pauseButton = new Button("Pause");
        pauseButton.setOnAction(Event -> {
            if (pauseButton.getText().equals("Pause")) {
                pauseButton.setText("Continue");
                animation.stop();
                field.requestFocus();
            } else {
                pauseButton.setText("Pause");
                animation.start();
                field.requestFocus();
            }
        });

        statusbar = new BorderPane();
        statusbar.setLeft(new HBox(textScore, poitns));
        statusbar.setCenter(gameOver);
        gameOver.setVisible(false);
        statusbar.setRight(pauseButton);

        field = new Pane(group);
        field.setId("field");
        field.setPrefSize(500, 500);

        field.setFocusTraversable(true);

        windowPane = new BorderPane();
        windowPane.setTop(statusbar);
        windowPane.setCenter(field);


        vBox = new VBox();
        vBox.getChildren().addAll(menuBar, windowPane);


        scene = new Scene(vBox);

        scene.widthProperty().addListener(( observable, oldValue, newValue ) -> {
            field.setPrefHeight(scene.getHeight());
        });

        scene.heightProperty().addListener(( observable, oldValue, newValue ) -> {
            field.setPrefHeight(scene.getWidth());
        });

        animation = new Animation(this);
        animation.start();
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        field.requestFocus();

    }

    public void show( Stage stage ) {
        stage.setTitle("PingPong");
        stage.setScene(scene);
        stage.show();
        field.requestFocus();
    }

    public Pane getField() {
        return field;
    }

    public Group getGroup() {
        return group;
    }

    public Label getPoitns() {
        return poitns;
    }

    public Label getGameOver() {
        return gameOver;
    }

    public void setTextScore( int score ) {
        poitns.setText(String.valueOf(score));
    }

    public int getTextScore() {
        return Integer.valueOf(poitns.getText());
    }

    public Button getPauseButton() {
        return pauseButton;
    }
}
