package application;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.stage.StageStyle;
import views.GameView;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        GameView gameView = new GameView(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
