package models;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import observers.StopWatchObservable;
import observers.StopWatchObserver;

import java.util.ArrayList;
import java.util.List;

public class StopWatch implements StopWatchObservable {

    private final List<StopWatchObserver> observers = new ArrayList<>();

    private String time;

    private int totalTime = 0;

    private final Timeline timeline;

    public StopWatch() {
        timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                e -> addTime(1000)));
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    @Override
    public void register(StopWatchObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for(StopWatchObserver observer : observers) {
            observer.update(this);
        }
    }

    public final String getStopWatch() {
        return "Time: " + time;
    }

    public void calculateTime() {
        int hours = totalTime / 3600000;
        int minutes = totalTime / 60000 % 60;
        int seconds = totalTime / 1000 % 60;
        time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        notifyAllObservers();
    }

    public void addTime(int amount) {
        totalTime += amount;
        calculateTime();
    }

    public void startStopWatch() {
        timeline.play();
    }

    public void stopStopWatch() {
        timeline.stop();
    }

    public void resetStopWatch() {
        stopStopWatch();
        totalTime = 0;
        calculateTime();
    }
}