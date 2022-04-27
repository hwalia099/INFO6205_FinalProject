package com.tictactoe.springboottictactieapi;

import com.tictactoe.springboottictactieapi.Classes.Play;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.tictactoe.springboottictactieapi.Classes.*;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;

@SpringBootTest
class SpringbootTictactieApiApplicationTests {

    @Test
    void shouldReturnMove() {
        char[][] board = new char[3][3];
        for(int i = 0; i < 3; i++) {
            board[i][0] = ' ';
            board[i][1] = ' ';
            board[i][2] = ' ';
        }
        Play play = new Play(board);
        PossiblePlay possibleMove= play.computerPlay('o', -1, 4, 0);
        assert possibleMove.getCol() >= 0;
        assert possibleMove.getRow() >= 0;
    }
    @Test
    public void noDrawWhenEmpty() {
    	char[][] c=new char[3][3];
      TicTacToe tacToe=new TicTacToe(c);
      assertFalse(tacToe.isDraw());
    }

}
