package controllers;

import models.Board;
import models.Solver;
import observers.SolverObserver;

public class SolverCtrl {

    private final Solver solver;

    public SolverCtrl(Board board) {
        solver = new Solver(board);
    }

    public void registerObserver(SolverObserver observer) {
        solver.register(observer);
    }

    public Solver getSolver() {
        return solver;
    }
}
