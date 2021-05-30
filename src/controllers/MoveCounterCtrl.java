package controllers;

import models.MoveCounter;
import observers.MoveCounterObserver;

public class MoveCounterCtrl {

    private final MoveCounter moveCounter;

    public MoveCounterCtrl() {
        moveCounter = new MoveCounter();
    }

    public void registerObserver(MoveCounterObserver observer) {
        moveCounter.register(observer);
    }

    public MoveCounter getMoveCounter() {
        return moveCounter;
    }
}
