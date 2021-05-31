package models;

import observers.SolverObservable;
import observers.SolverObserver;

import java.util.ArrayList;
import java.util.List;

public class Solver implements SolverObservable {

    private final List<SolverObserver> observers = new ArrayList<>();

    Board board;

    int boardSize;

    boolean[][] booleanBoard;

    int[][] solution;

    public Solver(Board board) {
        this.board = board;
    }

    @Override
    public void register(SolverObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for(SolverObserver observer : observers) {
            observer.update(this);
        }
    }

    public void run() {
        boardSize = board.getSize();

        booleanBoard = new boolean[boardSize][boardSize];
        solution = new int[boardSize][boardSize];

        System.out.println("Runs solver");
        fillBoard();

        solve();

        if(win()) {
            showSolution();
        } else {
            solverLoop();
        }

    }

    public void fillBoard() {
        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                booleanBoard[i][j] = board.getBoardMatrix()[i][j].getIsActive();
            }
        }
    }

    public void solverLoop() {
        for(int a = 0; a < boardSize; a++) {
            for(int b = 0; b < boardSize; b++) {
                if(a == b) {
                    click(a, 0);
                    if(win()) {
                        showSolution();
                        return;
                    } else {
                        click(a, 0);
                    }
                } else if(a >= b) {
                    continue;
                } else {
                    click(a, 0);
                    click(b, 0);
                    if(win()) {
                        showSolution();
                        return;
                    } else {
                        click(a, 0);
                        click(b, 0);
                    }
                }
                for(int c = 0; c < boardSize; c++) {
                    if(a >= b || b >= c) {
                        continue;
                    } else if(a == b && b == c) {
                        click(a, 0);
                        if(win()) {
                            showSolution();
                            return;
                        } else {
                            click(a, 0);
                        }
                    } else if(a == b || b == c || a == c) {
                        continue;
                    } else {
                        click(a, 0);
                        click(b, 0);
                        click(c, 0);
                        if(win()) {
                            showSolution();
                            return;
                        } else {
                            click(a, 0);
                            click(b, 0);
                            click(c, 0);
                        }
                    }
                }
            }
        }
        System.out.println("No solution found");

    }

    public void solve() {
        for(int i = 0; i < boardSize - 1; i++) {
            for(int j = 0; j < boardSize; j++) {
                if(booleanBoard[i][j]) {
                    try {
                        click(j, i + 1);
                    } catch(Exception ignored) {}
                }
            }
        }

    }

    public boolean win() {
        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                if(booleanBoard[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void showSolution() {
        for(int[] array : solution) {
            for(int number : array) {
                System.out.print(number % 2  + " ");
            }
            System.out.println();
        }

        System.out.println("Solution found");

    }

    public void click(int x, int y) {
        boolean state;

        state = booleanBoard[y][x];
        booleanBoard[y][x] = !state;

        switchAdjacent(x, y + 1);
        switchAdjacent(x, y - 1);
        switchAdjacent(x + 1, y);
        switchAdjacent(x - 1, y);

        solution[y][x] += 1;
        solve();

    }

    public void switchAdjacent(int x, int y) {
        boolean state;

        try {
            state = booleanBoard[y][x];
            booleanBoard[y][x] = !state;
        } catch(Exception ignored) {}
    }

}
