package observers;

public interface MoveCounterObservable {
    public void register(MoveCounterObserver observer);
    public void notifyAllObservers();
}
