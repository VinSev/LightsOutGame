package observers;

public interface MoveCounterObservable {
    void register(MoveCounterObserver observer);
    void notifyAllObservers();
}
