package models;

import observers.BoardObservable;
import observers.BoardObserver;

import java.util.ArrayList;
import java.util.List;

public class Board implements BoardObservable {

    private final List<BoardObserver> observers = new ArrayList<>();

    private int size;
    private Lamp[][] board;

    public Board(int size) {
        setSize(size);
    }

    @Override
    public void register(BoardObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for(BoardObserver observer : observers) {
            observer.update(this);
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        board = new Lamp[size][size];
        notifyAllObservers();
    }

    public Lamp[][] getBoardMatrix() {
        return board;
    }

    public void clickLamp(int row, int column ) {
        board[row][column].switchBackgroundColor();
    }

}
