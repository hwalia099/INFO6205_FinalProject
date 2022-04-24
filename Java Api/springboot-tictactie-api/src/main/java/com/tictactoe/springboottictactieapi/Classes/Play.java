package com.tictactoe.springboottictactieapi.Classes;

import java.io.*;
import java.util.logging.Logger;

public class Play {
    private final char COMP = 'o';
    private final char HUMAN = 'x';
    int maxLevel = 5;
    boolean isNewRecordInserted = false;

    private TicTacToe TTT;
    private Dictionary configurations;

    public Play(char[][] board) {
        TTT = new TicTacToe(3,3,3, board);
    }

    public PossiblePlay computerPlay(char symbol, int highest_score, int lowest_score,int level) {
        char opponent; // Opponent's symbol
        int value;
        if (level == 0) /* Create new hash table */
            configurations = TTT.createDictionary();
            try {
                FileInputStream fis=new FileInputStream("Menace_self_Learn.dat");
                ObjectInputStream ois = new ObjectInputStream(fis);
                configurations = (Dictionary) ois.readObject();

            }catch(Exception ex){System.out.println(configurations.toString());}
        if (symbol == COMP) {
            opponent = HUMAN;
            value = -1;
        } else {
            opponent = COMP;
            value = 4;
        }
        PossiblePlay reply; // Opponent's best reply
        int lookupVal;
        int board_size = 3;
        int row, column;
        int bestRow = -1;
        int bestColumn = -1; // Position of best play
        row = (int)(Math.random() * board_size);    // select a random position
        for (int r = 0; r < board_size; r++) {
            column = (int)(Math.random() * board_size);
            for (int c = 0; c < board_size; c++) {
                if (TTT.squareIsEmpty(row, column)) { // Empty position         //check if the position is empty
                    TTT.storePlay(row, column, symbol); // Store next play    //store the symbol in that position if it is empty
                    if (TTT.wins(symbol) || TTT.isDraw() || (level >= maxLevel))
                        // Game ending situation or max number of levels
                        // reached
                        reply = new PossiblePlay(TTT.evalBoard(), row, column);
                    else {
                        lookupVal = TTT.repeatedConfig(configurations);
                        if (lookupVal != -1)
                            reply = new PossiblePlay(lookupVal, row, column);
                        else {
                            reply = computerPlay(HUMAN, highest_score, lowest_score, level + 1);
                            if (TTT.repeatedConfig(configurations) == -1) {
                                isNewRecordInserted = true;
                                TTT.insertConfig(configurations, reply.getScore(), 0);

                            }
                        }
                    }
                    TTT.storePlay(row, column, ' ');

                    if ((symbol == COMP && reply.getScore() > value)
                            || (symbol == HUMAN && reply.getScore() < value)) {
                        bestRow = row;
                        bestColumn = column;
                        value = reply.getScore();

                        /* Alpha/beta cut */
                        if (symbol == COMP && value > highest_score)
                            highest_score = value;
                        else if (symbol == HUMAN && value < lowest_score)
                            lowest_score = value;

                        if (highest_score >= lowest_score)
                            return new PossiblePlay(value, bestRow, bestColumn);
                    }

                }
                column = (column + 1) % board_size;
            }
            row = (row + 1) % board_size;
        }

        if(isNewRecordInserted) {
            try
            {
                FileOutputStream fos = new FileOutputStream("Menace_self_Learn.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(configurations);

            }catch(Exception ex){
                System.err.println(ex.getMessage());
            }
        }
        return new PossiblePlay(value, bestRow, bestColumn);
    }
}
