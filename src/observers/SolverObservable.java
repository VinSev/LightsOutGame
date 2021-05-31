package observers;

public interface SolverObservable {
    void register(SolverObserver observer);
    void notifyAllObservers();
}
