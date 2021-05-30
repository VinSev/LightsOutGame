package views;

import controllers.BoardCtrl;
import controllers.GameCtrl;
import controllers.MoveCounterCtrl;
import controllers.StopWatchCtrl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Board;
import models.Lamp;
import observers.*;

import java.io.IOException;

public class GameView implements BoardObserver, MoveCounterObserver, StopWatchObserver {
    //ScreenSize
    int WIDTH = 760;
    int HEIGHT = 710;

    private final Stage primaryStage;

    private GameCtrl gameCtrl;

    private final BoardCtrl boardCtrl;
    private final MoveCounterCtrl moveCounterCtrl;
    private final StopWatchCtrl stopWatchCtrl;

    public GameView(Stage primaryStage) {
        this.primaryStage = primaryStage;

        gameCtrl = gameCtrl.getInstanceGameCtrl();

        boardCtrl = gameCtrl.getInstanceBoardCtrl();
        boardCtrl.registerObserver(this);

        moveCounterCtrl = gameCtrl.getInstanceMoveCounterCtrl();
        moveCounterCtrl.registerObserver(this);

        stopWatchCtrl = gameCtrl.getInstanceStopWatchCtrl();
        stopWatchCtrl.registerObserver(this);

        try {
            createPrimaryStage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(BoardObservable observable) {
        try {
            createPrimaryStage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(MoveCounterObservable observable) {
        String moveCounter = moveCounterCtrl.getMoveCounter().getMoveCounter();
        gameCtrl.getMoveCounterLabel().setText(moveCounter);
    }

    @Override
    public void update(StopWatchObservable observable) {
        String timer = stopWatchCtrl.getStopWatch().getStopWatch();
        gameCtrl.getTimerLabel().setText(timer);
    }

    private void createPrimaryStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/game.fxml"));
        loader.setController(gameCtrl);
        Parent root = loader.load();

        createGameBoard();

        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }

    private void createGameBoard() {
        FlowPane boardPane = gameCtrl.getBoardPane();
        boardPane.getChildren().removeAll();
        Board board = boardCtrl.getBoard();

        int boardSize = board.getSize();

        Lamp[][] boardMatrix = board.getBoardMatrix();

        for(int row = 0; row < boardSize; row++) {
            for(int column = 0; column < boardSize; column++) {
                Button lamp = createLamp(boardMatrix, row, column, boardSize);
                boardPane.getChildren().add(lamp);
            }
        }
        boardCtrl.randomizeBoard();
    }

    private Button createLamp(Lamp[][] boardMatrix, int row, int column, int boardSize) {
        boardMatrix[row][column] = new Lamp(new Button());
        boardMatrix[row][column].setBackgroundColor(Color.GRAY);

        Button lamp = boardMatrix[row][column].getLamp();
        lamp.setPrefSize(610 / boardSize - 2, 610 / boardSize - 2);

        lamp.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            boardCtrl.clickLampOnBoard(row, column);
            boolean hasWon = gameCtrl.winGameDetection();
            if(hasWon) {
                stopWatchCtrl.getStopWatch().stopStopWatch();
            }
            int moveCount = moveCounterCtrl.getMoveCounter().getMoveCount();
            if(moveCount == 0) {
                stopWatchCtrl.getStopWatch().startStopWatch();
            }
            moveCounterCtrl.getMoveCounter().addToMoveCount();
        } // Zou het beter zijn om dit in een LampCtrl ofzo te behandelen ipv in de GameView?
        );
        return lamp;
    }

}