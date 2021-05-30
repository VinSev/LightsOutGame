package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import observers.MoveCounterObservable;
import observers.MoveCounterObserver;

import java.util.ArrayList;
import java.util.List;

public class MoveCounter implements MoveCounterObservable {

    private final List<MoveCounterObserver> observers = new ArrayList<MoveCounterObserver>();

    private int moveCount;

    public MoveCounter() {
        moveCount = 0;
    }

    @Override
    public void register(MoveCounterObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for(MoveCounterObserver observer : observers) {
            observer.update(this);
        }
    }

    public String getMoveCounter() {
        return "Moves: " + moveCount;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
        notifyAllObservers();
    }

    public void addToMoveCount() {
        moveCount += 1;
        notifyAllObservers();
    }

}
