package controllers;

import models.Board;
import observers.BoardObserver;

import java.util.Random;

public class BoardCtrl {

    public static BoardCtrl boardCtrl;

    private Board board;

    public BoardCtrl() {
        board = new Board(5);
    }

    public void registerObserver(BoardObserver observer) {
        board.register(observer);
    }

    public Board getBoard() {
        return board;
    }

    public void clickLampOnBoard(int row, int column) {
        board.clickLamp(row, column);
        switchAdjacentLampOnBoard(row - 1, column);
        switchAdjacentLampOnBoard(row, column + 1);
        switchAdjacentLampOnBoard(row + 1, column);
        switchAdjacentLampOnBoard(row, column - 1);
    }

    public void switchAdjacentLampOnBoard(int row, int column) {
        try {
            board.clickLamp(row, column);
        } catch(ArrayIndexOutOfBoundsException ignored) {}
    }

    public void randomizeBoard() {
        int boardSize = board.getSize();
        Random rand = new Random();

        int n = boardSize * boardSize;

        for (int i = 0; i < n; i++) {
            int row = rand.nextInt(boardSize);
            int column = rand.nextInt(boardSize);

            clickLampOnBoard(row, column);
        }
    }
}
