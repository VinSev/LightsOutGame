package controllers;

import models.StopWatch;
import observers.StopWatchObserver;

public class StopWatchCtrl {

    public static StopWatchCtrl stopWatchCtrl;

    private StopWatch stopWatch;

    public StopWatchCtrl() {
        stopWatch = new StopWatch();
    }

    public void registerObserver(StopWatchObserver observer) {
        stopWatch.register(observer);
    }

    public StopWatch getStopWatch() {
        return stopWatch;
    }
}
