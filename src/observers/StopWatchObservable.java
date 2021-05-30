package observers;

public interface StopWatchObservable {
    void register(StopWatchObserver observer);
    void notifyAllObservers();
}
