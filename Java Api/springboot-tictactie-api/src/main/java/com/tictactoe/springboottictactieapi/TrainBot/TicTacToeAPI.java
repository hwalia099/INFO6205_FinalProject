package com.tictactoe.springboottictactieapi.TrainBot;

public class TicTacToeAPI {
    private String player1, player2, winType;

    public TicTacToeAPI() {
        super();
        player1 = "O";
        player2 = "X";
        winType = " ";
    }

    public String changeTurns(String turn) {
        if (turn.equals(" "))
            return player1;
        else if (turn.equals(player1))
            turn = player2;
        else
            turn = player1;
        return turn;
    }

    public Square winner(Square[][] board, int rows, int columns) {
        // check column wins
        for (int i = 0; i < rows; i++) {
            if (board[i][0].getPiece().getValue().equals(board[i][1].getPiece().getValue())
                    && board[i][1].getPiece().getValue().equals(board[i][2].getPiece().getValue())
                    && board[i][0].getPiece().getValue().equals(board[i][0].getPiece().getValue())
                    && !board[i][0].getPiece().getValue().equals(" ") && !board[i][1].getPiece().getValue().equals(" ")
                    && !board[i][2].getPiece().getValue().equals(" ")) {
                winType = "column";
                return board[i][0];
            }
        }
        // checks row wins
        for (int i = 0; i < columns; i++) {
            if (board[0][i].getPiece().getValue().equals(board[1][i].getPiece().getValue())
                    && board[1][i].getPiece().getValue().equals(board[2][i].getPiece().getValue())
                    && board[0][i].getPiece().getValue().equals(board[2][i].getPiece().getValue())
                    && !board[0][i].getPiece().getValue().equals(" ") && !board[1][i].getPiece().getValue().equals(" ")
                    && !board[2][i].getPiece().getValue().equals(" ")) {
                winType = "row";
                return board[0][i];
            }
        }
        // checks diagonal wins
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])
                && !board[0][0].getPiece().getValue().equals(" ")) {
            winType = "diagonal";
            return board[0][0];
        }
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])
                && !board[0][2].getPiece().getValue().equals(" ")) {
            winType = "diagonal";
            return board[0][2];
        }
        return null;
    }

}
