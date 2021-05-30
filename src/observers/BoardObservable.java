package observers;

public interface BoardObservable {
    public void register(BoardObserver observer);
    public void notifyAllObservers();
}
