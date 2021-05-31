package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.layout.FlowPane;
import models.Board;
import models.Lamp;
import models.MoveCounter;
import models.StopWatch;

public class GameCtrl {

    //MainMenuPane
    @FXML
    private Label timerLabel;
    @FXML
    private Label moveCounterLabel;
    @FXML
    private void solveGameAction() {
        solverCtrl.getSolver().run();
    }
    @FXML
    private void resetGameAction() {
        Board board = boardCtrl.getBoard();
        int boardSize = board.getSize();
        resetGame(boardSize);
    }
    @FXML
    private void exitGameAction() {
        System.exit(0);
    }

    //BoardPane
    @FXML
    private FlowPane boardPane;

    //BoardSizeMenuPane
    @FXML
    private void setBoardSizeTo3x3Action() {
        resetGame(3);
    }
    @FXML
    private void setBoardSizeTo4x4Action() {
        resetGame(4);
    }
    @FXML
    private void setBoardSizeTo5x5Action() {
        resetGame(5);
    }

    private static GameCtrl gameCtrl;
    private static BoardCtrl boardCtrl;
    private static MoveCounterCtrl moveCounterCtrl;
    private static SolverCtrl solverCtrl;
    private static StopWatchCtrl stopWatchCtrl;

    public static BoardCtrl getInstanceBoardCtrl() {
        if(boardCtrl == null) {
            boardCtrl = new BoardCtrl();
        }
        return boardCtrl;
    }

    public static GameCtrl getInstanceGameCtrl() {
        if(gameCtrl == null) {
            gameCtrl = new GameCtrl();
        }
        return gameCtrl;
    }

    public static MoveCounterCtrl getInstanceMoveCounterCtrl() {
        if(moveCounterCtrl == null) {
            moveCounterCtrl = new MoveCounterCtrl();
        }
        return moveCounterCtrl;
    }

    public static SolverCtrl getInstanceSolverCtrl() {
        if(solverCtrl == null) {
            solverCtrl = new SolverCtrl(boardCtrl.getBoard());
        }
        return solverCtrl;
    }

    public static StopWatchCtrl getInstanceStopWatchCtrl() {
        if(stopWatchCtrl == null) {
            stopWatchCtrl = new StopWatchCtrl();
        }
        return stopWatchCtrl;
    }

    public boolean winGameDetection() {
        Board board = boardCtrl.getBoard();
        Lamp[][] boardMatrix = board.getBoardMatrix();
        int boardSize = board.getSize();

        for(int row = 0; row < boardSize; row++) {
            for(int column = 0; column < boardSize; column++) {
                if(boardMatrix[row][column].getIsActive()) return false;
            }
        }
        return true;
    }

    public void resetGame(int boardSize) {
        MoveCounter moveCounter = moveCounterCtrl.getMoveCounter();
        StopWatch stopWatch = stopWatchCtrl.getStopWatch();
        Board board = boardCtrl.getBoard();

        moveCounter.setMoveCount(0);
        stopWatch.resetStopWatch();
        board.setSize(boardSize);
        boardCtrl.randomizeBoard();
    }

    public FlowPane getBoardPane() {
        return boardPane;
    }

    public Label getTimerLabel() {
        return timerLabel;
    }

    public Label getMoveCounterLabel() {
        return moveCounterLabel;
    }
}