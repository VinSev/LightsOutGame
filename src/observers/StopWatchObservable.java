package observers;

public interface StopWatchObservable {
    public void register(StopWatchObserver observer);
    public void notifyAllObservers();
}
