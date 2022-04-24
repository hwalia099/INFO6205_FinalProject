package com.tictactoe.springboottictactieapi;

import com.tictactoe.springboottictactieapi.Classes.Play;
import com.tictactoe.springboottictactieapi.Classes.PossiblePlay;
import com.tictactoe.springboottictactieapi.Classes.RequestObject;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class TicTacToeApiController {

    @GetMapping("/welcome")
    public String Welcome() {
        return "Tic Tac Toe";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/process", method = RequestMethod.POST)
        public PossiblePlay GetBestMove(@RequestBody List<RequestObject> request) {
        char[][] board = new char[3][3];
        for(int i = 0; i < request.size(); i++) {
            board[i][0] = request.get(i).left.charAt(0);
            board[i][1] = request.get(i).center.charAt(0);
            board[i][2] = request.get(i).right.charAt(0);
        }
        
        var play = new Play(board);
        return play.computerPlay('o', -1, 4, 0);
    }
}
