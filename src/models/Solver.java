package models;

import observers.SolverObservable;
import observers.SolverObserver;

import java.util.ArrayList;
import java.util.List;

public class Solver implements SolverObservable {

    private final List<SolverObserver> observers = new ArrayList<>();

    Board board;
    int boardSize;

    int[][] intBoard;
    int[][] solutionBoard;

    

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
        intBoard = new int[boardSize][boardSize];
        solutionBoard = new int[boardSize][boardSize];

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
        for(int row = 0; row < boardSize; row++) {
            for(int column = 0; column < boardSize; column++) {
                if(board.getBoardMatrix()[row][column].getIsActive()) {
                    intBoard[row][column] = 1;
                } else {
                    intBoard[row][column] = 0;
                }
            }
        }
    }

    public void solverLoop() {
        for(int a = 0; a < boardSize; a++) {
            for(int b = 0; b < boardSize; b++) {
                if(a == b) {
                    click(0, a);
                    if(win()) {
                        showSolution();
                        return;
                    } else {
                        click(0, a);
                    }
                } else if(a >= b) {
                    continue;
                } else {
                    click(0, a);
                    click(0, b);
                    if(win()) {
                        showSolution();
                        return;
                    } else {
                        click(0, a);
                        click(0, b);
                    }
                }
                for(int c = 0; c < boardSize; c++) {
                    if(a >= b || b >= c) {
                        continue;
                    } else if(a == b && b == c) {
                        click(0, a);
                        if(win()) {
                            showSolution();
                            return;
                        } else {
                            click(0, a);
                        }
                    } else if(a == b || b == c || a == c) {
                        continue;
                    } else {
                        click(0, a);
                        click(0, b);
                        click(0, c);
                        if(win()) {
                            showSolution();
                            return;
                        } else {
                            click(0, a);
                            click(0, b);
                            click(0, c);
                        }
                    }
                }
            }
        }
        System.out.println("No solution found");

    }

    public void solve() {
        for(int row = 0; row < boardSize - 1; row++) {
            for(int column = 0; column < boardSize; column++) {
                if(intBoard[row][column] % 2 == 1) {
                    try {
                        click(row + 1, column);
                    } catch(ArrayIndexOutOfBoundsException ignored) {}
                }
            }
        }
    }

    public boolean win() {
        for(int row = 0; row < boardSize; row++) {
            for(int column = 0; column < boardSize; column++) {
                if(intBoard[row][column] % 2 == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public void showSolution() {
        for(int[] array : solutionBoard) {
            for(int number : array) {
                System.out.print(number % 2 + " ");
            }
            System.out.println();
        }
        System.out.println("Solution found");
    }

    public void click(int row, int column) {
        intBoard[row][column] += 1;
        solutionBoard[row][column] += 1;

        switchAdjacent(row + 1, column);
        switchAdjacent(row - 1, column);
        switchAdjacent(row, column + 1);
        switchAdjacent(row, column - 1);

        solve();
    }

    public void switchAdjacent(int row, int column) {
        try {
            intBoard[row][column] += 1;
        } catch(ArrayIndexOutOfBoundsException ignored) {}
    }

}
