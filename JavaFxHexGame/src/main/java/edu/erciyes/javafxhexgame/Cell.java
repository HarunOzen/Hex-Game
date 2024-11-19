package edu.erciyes.javafxhexgame;


public class Cell {
    private int row;
    private int col;
    private Players owner;
    private boolean visited;

    public Cell(int row, int col, Players owner) {
        this.row = row;
        this.col = col;
        this.owner = owner;
        this.visited = false;
    }

    public Players getOwner() {
        return owner;
    }

    public void setOwner(Players owner) {
        this.owner = owner;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
}
}

