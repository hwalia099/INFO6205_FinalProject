package com.tictactoe.springboottictactieapi.TrainBot;

import com.tictactoe.springboottictactieapi.Classes.Play;

public class TicTacToe
{
    private String[][] board;
    private TicTacToeAPI api;
    private String player1, player2;
    private int movesmade;
    private int rows, columns;
    private Board boardA;
    private boolean gameRunning;

    public TicTacToe()
    {
        gameRunning = false;
        api = new TicTacToeAPI();
        board=new String[3][3];
        player1="O";
        player2="X";
        movesmade=0;
        rows=board.length;
        columns=board[0].length;
        boardA = new Board();
    }

    public static void main(String args[])
    {
        TicTacToe ttt=new TicTacToe();
        ttt.run();
    }

    public void run()
    {
        gameRunning = true;
        System.out.println("\n\n");
        String input=player1;
        String playAgainStatus="";
        boolean endGame=false;
        setUpBoard();

        do
        {
            displayUpdatedBoard();
            makeMove("X");
            //input=changeTurns(input);
            if(movesmade == 8) {
                movesmade++;
            } else {
                movesmade += 2;
            }

            if((movesmade==9) && Winner().equals(" "))
            {
                displayUpdatedBoard();
                gameRunning = false;
                System.out.println("TIE!");
            }
            else if((movesmade<=9) && !(Winner().equals(" ")))
            {
                displayUpdatedBoard();
                gameRunning = false;
                System.out.println("The winner is :"+Winner());
            }
        }while(gameRunning);
        return;
    }

    public void setUpBoard()
    {
        System.out.println("\nInfo6205 Project- The Menace\n");
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<columns;j++)
            {
                board[i][j]=" ";
            }
        }
    }

    public void displayUpdatedBoard()
    {
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<columns;j++)
            {
                System.out.print("[" +board[i][j]+ "]");
            }
            System.out.println();
        }
        System.out.println();

    }

    public void makeMove(String move)
    {
        boolean moveMade=false;
        int playerRow=0, playerColumn=0;
        do{
            char[][] boardOriginal = new char[3][3];
            for(int i = 0; i < 3; i++) {
                boardOriginal[i][0] = board[i][0] == "" ? ' ' : board[i][0].charAt(0);
                boardOriginal[i][1] = board[i][1] == "" ? ' ' : board[i][1].charAt(0);
                boardOriginal[i][2] = board[i][2] == "" ? ' ' : board[i][2].charAt(0);
            }
            var play = new Play(boardOriginal);
            if (!boardA.playerMoveDone) {
                var aiMove = play.computerPlay('x', -1, 4, 0);
                playerRow = aiMove.getRow() + 1;
                playerColumn = aiMove.getCol() + 1;
            }
            if((playerRow>=1 && playerRow<=rows) && (playerColumn>=1 && playerColumn<=columns) && board[playerRow-1][playerColumn-1].equals(" "))
            {
                board[playerRow-1][playerColumn-1]=move;
                boardA.playerMoveDone = true;
                boardA.board[playerColumn - 1][playerRow - 1].setPiece(new Piece("X", playerRow, playerColumn));
                boardA.update();
                board[0][0] = boardA.board[0][0].getPiece().getValue();
                board[0][1] = boardA.board[1][0].getPiece().getValue();
                board[0][2] = boardA.board[2][0].getPiece().getValue();
                board[1][0] = boardA.board[0][1].getPiece().getValue();
                board[1][1] = boardA.board[1][1].getPiece().getValue();
                board[1][2] = boardA.board[2][1].getPiece().getValue();
                board[2][0] = boardA.board[0][2].getPiece().getValue();
                board[2][1] = boardA.board[1][2].getPiece().getValue();
                board[2][2] = boardA.board[2][2].getPiece().getValue();
                moveMade = true;
            }
            else
            {
                System.out.println("Invalid move! Try again");
            }
        } while(!moveMade);
    }

    public String changeTurns(String turn)
    {
        if(turn==player1)
            turn=player2;
        else
            turn=player1;
        return turn;
    }

    public String Winner()
    {

        //for rows

        for(int i=0;i<rows;i++)
        {
            if((board[i][0]).equals(board[i][1]) && (board[i][1].equals(board[i][2])))
            {
                return board[i][0];
            }
        }


        //for columns

        for(int i=0;i<columns;i++)
        {
            if((board[0][i]).equals(board[1][i]) && (board[1][i].equals(board[2][i])))
            {
                return board[0][i];
            }
        }

        //for diagonals


        if((board[0][0]).equals(board[1][1]) && (board[1][1].equals(board[2][2])))
        {
            return board[0][0];
        }

        if((board[0][2]).equals(board[1][1]) && (board[1][1].equals(board[2][0])))
        {
            return board[0][2];
        }
        return " ";
    }
}



