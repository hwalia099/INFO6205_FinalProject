package com.tictactoe.springboottictactieapi.TrainBot;

public class Board {
    public Square[][] board;
    private Square winner;
    private TicTacToeAPI api;
    private String currentTurn;
    private int rows, cols, movesMade;
    private boolean winnerFound;
    private String playerChoice;
    public boolean playerMoveDone;

    public Board() {
        board = new Square[3][3];
        board[0][0] = new Square(0, 0, new Piece(" ", 0, 0));
        board[0][1] = new Square(1, 0, new Piece(" ", 0, 1));
        board[0][2] = new Square(2, 0, new Piece(" ", 0, 2));
        board[1][0] = new Square(0, 1, new Piece(" ", 1, 0));
        board[1][1] = new Square(1, 1, new Piece(" ", 1, 1));
        board[1][2] = new Square(2, 1, new Piece(" ", 1, 0));
        board[2][0] = new Square(0, 2, new Piece(" ", 2, 0));
        board[2][1] = new Square(1, 2, new Piece(" ", 2, 1));
        board[2][2] = new Square(2, 2, new Piece(" ", 2, 2));
        api = new TicTacToeAPI();
        rows = board.length;
        cols = board[0].length;
        currentTurn = " ";
        winner = null;
        winnerFound = false;
        movesMade = 0;
        playerChoice = "";
        playerMoveDone = false;

    }

    public void update() {
        if (currentTurn.equals(" "))
            currentTurn = api.changeTurns(currentTurn);
        if (playerMoveDone) {
            if (board != null) {
                computerMove();
                currentTurn = api.changeTurns(currentTurn);
                playerMoveDone = false;
            }
        }
        winner = api.winner(board, rows, cols);
        if (winner != null && !winner.getPiece().getValue().equals(" ")) {
            winnerFound = true;
        }
        if (winner == null && movesMade == 9) {
            winnerFound = true;
        }
    }

    public void computerMove() {
        Square[][] tempBoard = copyBoard(board);
        Square tempWinner = null;
        String comp = "O";
        String playMove = "X";
        // Take the winning move if available
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (tempBoard[j][i].getPiece().getValue().equals(" ")) {
                    tempBoard[j][i].setPiece(new Piece(comp, i + 1, j + 1));
                    tempWinner = api.winner(tempBoard, 3, 3);
                    if (tempWinner != null && winner == null) {
                        board[j][i].setPiece(new Piece(comp, i + 1, j + 1));
                        movesMade++;
                        return;
                    } else if (winner == null) {
                        tempBoard[j][i].setPiece(new Piece(" ", i + 1, j + 1));
                        tempWinner = null;
                    }
                }
            }

        }

        // Stop player from winning the game
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (tempBoard[j][i].getPiece().getValue().equals(" ")) {
                    tempBoard[j][i].setPiece(new Piece(playMove, i + 1, j + 1));
                    tempWinner = api.winner(tempBoard, rows, cols);
                    if (tempWinner != null && winner == null) {
                        board[j][i].setPiece(new Piece(comp, i + 1, j + 1));
                        movesMade++;
                        return;
                    } else {
                        tempBoard[j][i].setPiece(new Piece(" ", i + 1, j + 1));
                        tempWinner = null;
                    }
                }
            }
        }

        /**
         * Select center if open
         * */
        if (board[1][1].getPiece().getValue().equals(" ")) {
            board[1][1].setPiece(new Piece(comp, 2, 2));
            movesMade++;
            return;
        }
        /**
         * Select any corners
         * */
        for (int i = (int) (Math.random() * rows); i < rows; i++) {
            for (int j = (int) (Math.random() * rows); j < cols; j++) {
                if (board[j][i].getPiece().getValue().equals(" ") && (i != 1 || j != 1)) {
                    board[j][i].setPiece(new Piece(comp, j, i));
                    movesMade++;
                    return;
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[j][i].getPiece().getValue().equals(" ")) {
                    board[j][i].setPiece(new Piece(comp, j, i));
                    movesMade++;
                    return;
                }
            }
        }
    }

    public Square[][] copyBoard(Square[][] b) {
        Square[][] toReturn = new Square[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Piece p = b[i][j].getPiece();
                int row = b[i][j].getRow();
                int col = b[i][j].getCol();
                toReturn[i][j] = new Square(row, col, new Piece(p.getValue(), p.getRow(), p.getCol()));
            }
        }
        return toReturn;
    }

}
