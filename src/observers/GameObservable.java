package observers;

public interface GameObservable {
    public void register(BoardObserver observer);
    public void notifyAllObservers();
}
