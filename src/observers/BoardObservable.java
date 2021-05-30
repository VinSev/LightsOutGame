package observers;

public interface BoardObservable {
    void register(BoardObserver observer);
    void notifyAllObservers();
}
