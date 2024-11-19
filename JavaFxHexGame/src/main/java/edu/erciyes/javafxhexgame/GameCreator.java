package edu.erciyes.javafxhexgame;


public class GameCreator {
    private int boardSize=5;
    private Players currentPlayer;
    private Cell[][] board;
    private int redMoves = 0;
    private int blueMoves = 0;

    public GameCreator(int boardSize) {
        this.boardSize = boardSize;
        this.currentPlayer = Players.Blue; // Oyunun başlangıcında oyuncu mavi ile başlar
        this.board = new Cell[boardSize][boardSize];
        initializeBoard(); // Tahtayı başlat
    }

    private void initializeBoard() {
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                board[row][col] = new Cell(row, col, null); // Başlangıçta tüm hücreler boş
            }
        }
    }

    public boolean isValidMove(int row, int col) {
        if (row < 0 || row >= boardSize || col < 0 || col >= boardSize) {
            return false; // Tahta sınırları dışında
        }
        return board[row][col].getOwner() == null; // Hücre boş olmalı
    }

    public void makeMove(int row, int col) {
        if (isValidMove(row, col)) {
            board[row][col].setOwner(currentPlayer);
            if (currentPlayer == Players.Red) {
                redMoves++;
            } else {
                blueMoves++;
            }
            currentPlayer = (currentPlayer == Players.Blue) ? Players.Red : Players.Blue; // Oyuncuyu değiştir
        }
    }

    public boolean checkWinCondition(Players player) {
        for (Cell[] row : board) {
            for (Cell cell : row) {
                cell.setVisited(false); // Tüm hücreleri ziyaret edilmemiş olarak ayarla
            }
        }
        // Oyuncunun başlangıç kenarındaki tüm hücreleri kontrol et
        if (player == Players.Blue) {
            for (int i = 0; i < boardSize; i++) {
                if (board[0][i].getOwner() == player && dfs(0, i, player)) {
                    return true;
                }
            }
        } else if (player == Players.Red) {
            for (int i = 0; i < boardSize; i++) {
                if (board[i][boardSize - 1].getOwner() == player && dfs(i, boardSize - 1, player)) {
                    return true;
                }
            }
        }
        return false; // Kazanan bulunamadı
    }

    private boolean dfs(int row, int col, Players player) {
        if ((player == Players.Blue && row == boardSize - 1) || (player == Players.Red && col == 0)) {
            return true; // Oyuncu karşı kenara ulaştı
        }

        // Ziyaret edildiğini işaretle
        board[row][col].setVisited(true);

        // Komşu hücreleri kontrol et (6 yön)
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0 || // Aynı hücreyi kontrol etme
                        row + i < 0 || row + i >= boardSize || col + j < 0 || col + j >= boardSize || // Sınır kontrolü
                        board[row + i][col + j].getOwner() != player || // Farklı oyuncuya ait hücre
                        board[row + i][col + j].isVisited()) { // Zaten ziyaret edilmiş hücre
                    continue;
                }

                if (dfs(row + i, col + j, player)) {
                    return true; // Karşı kenara ulaşıldı
                }
            }
        }

        return false; // Karşı kenara ulaşılamadı
}



    public Players getCurrentPlayer() {
        return currentPlayer;
    }
    public int getRedMoves() {
        return redMoves;
    }

    public int getBlueMoves() {
        return blueMoves;
}
}

